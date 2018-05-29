package com.bayviewglen.zork.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.bayviewglen.zork.Combat;
import com.bayviewglen.zork.Command;
import com.bayviewglen.zork.CommandWords;
import com.bayviewglen.zork.Inventory;
import com.bayviewglen.zork.Parser;
import com.bayviewglen.zork.Room;
import com.bayviewglen.zork.items.Armor;
import com.bayviewglen.zork.items.Food;
import com.bayviewglen.zork.items.Item;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.items.weapons.BareHands;

public class Player extends Entity{

	private Room currentRoom;
	private Inventory inventory;
	private Armor equippedArmor;
	private Weapon equippedWeapon;
	private int maxHealth;
	private HashMap<String, Runnable> methods = new HashMap<String, Runnable>();
	private int damageScaler;

	public Player(Room startingRoom) {
		super("");
		currentRoom = startingRoom;
		inventory = new Inventory();
		maxHealth = 500;
		health = 500;
		equippedWeapon = new BareHands();
		equippedArmor = null;
		damageScaler = 1;
	}

	/**
	 * Parse command, process (that is: execute) the command. If this command ends
	 * the game, true is returned, otherwise false is returned.
	 */
	public boolean act() {
		if (getHealth() <= 0) {
			return true;
		}
		
		poison();		
		Command command = Parser.getCommand();
		System.out.println();

		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		if (command.is("help"))
			printHelp();
		else if (command.is("location"))
			printLocation();
		else if (command.is("welfare"))
			System.out.println(this);
		else if (command.is("move"))
			goRoom(command);
		else if (command.is("search"))
			searchRoom();
		else if (command.is("inventory"))
			printInventory();
		else if (command.is("eat"))
			eat(command);
		else if (command.is("pickup"))
			pickupItem(command);
		else if (command.is("drop"))
			dropItem(command);
		else if (command.is("equip"))
			equipItem(command);
		else if (command.is("equipment"))
			printEquipment();
		else if (command.is("unequip"))
			unequip(command);
		else if (command.is("talk"))
			talk(command);
		else if (command.is("quit")) {
			if (command.hasManyWords(2))
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		}
		return false;
	}

	/**
	 * Prints location of the player
	 */
	public void printLocation() {
		System.out.println(currentRoom.longDescription());
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic message
	 * and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost? Are you dumb? You need help?");
		System.out.println("Don't worry. I got you buddy.");
		System.out.println();
		System.out.println("Your actions are:");
		Parser.showCommands();
	}

	/**
	 * Prints inventory
	 */
	private void printInventory() {
		System.out.println("You have " + inventory);
	}

	/**
	 * Prints equipment()
	 */
	private void printEquipment() {
		String weapon = "Weapon: " + equippedWeapon.longDescription();
		String armor = equippedArmor == null ? "No armor equipped" : "Armor: " + equippedArmor.longDescription();
		System.out.println(weapon);
		System.out.println();
		System.out.println(armor);
	}

	/**
	 * Searches the room
	 */
	private void searchRoom() {
		System.out.println("Items: " + currentRoom.inventory);
		System.out.println("People: " + currentRoom.entities.getNPCList());
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasManyWords(2)) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		move(direction);
	}

	/**
	 * Moves character to next room
	 * 
	 * @param direction
	 */
	private void move(String direction) {

		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			if (nextRoom.entities.hasMonsters()) {
				Combat entranceFight = new Combat(this, nextRoom.entities.getMonsters());
				if (entranceFight.chooseEngage()) {
					if (entranceFight.engageInCombat()) {
						currentRoom = nextRoom;
						currentRoom.entities.removeMonsters();
						System.out.println(currentRoom.longDescription());
					} else {
						return;
					}
				}
			} else {
				currentRoom = nextRoom;
				System.out.println(currentRoom.longDescription());
			}
		}
	}

	/**
	 * Eats food
	 */
	private void eat(Command command) {
		if (!command.hasManyWords(2)) {
			System.out.println("Eat what?");
			return;
		}

		String foodName = Command.mergeFinalWords(command, 1);
		String foodId = foodName.replaceAll("\\s","");
		Food food = (Food) inventory.getItem(foodId);

		if (food != null) {

			if (health >= maxHealth) {
				System.out.println("You are full");
				return;
			}

			for (int i = 0; i < 3; i++) {
				System.out.print("*munch*");
				try {
					TimeUnit.MILLISECONDS.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int foodHealthPoint = food.getHealthPoints();
			health += foodHealthPoint;
			inventory.reduceAmount(foodId, 1);

			if (health > maxHealth)
				health = maxHealth;

			System.out.println("\nHealth: " + health);

		} else {
			System.out.println("You have no " + foodName + "s");
		}
	}

	/**
	 * Drops an item
	 * 
	 * @param command
	 */
	private void dropItem(Command command) {
		if (!command.hasManyWords(2)) {
			System.out.println("Drop what?");
			return;
		}

		String itemName = Command.mergeFinalWords(command, 1);
		String itemId = itemName.replaceAll("\\s","");
		Item item = inventory.getItem(itemId);

		if (item != null) {

			currentRoom.inventory.addToInventory(item, item.getAmount());
			inventory.removeItem(itemId);

			if (item.isConsumable()) {
				int amount = item.getAmount();
				if (amount > 1)
					System.out.println("You dropped " + amount + " " + item.getName() + "s.");
				else
					System.out.println("You dropped " + amount + " " + item.getName());
			} else {
				System.out.println("You dropped your " + item.getName());
			}
		} else {
			System.out.println("You have no " + itemName + "s");
		}
	}

	/**
	 * Picks up an item
	 * 
	 * @param command
	 */
	private void pickupItem(Command command) {
		if (!command.hasManyWords(2)) {
			System.out.println("Pickup what?");
			return;
		}

		String itemName = Command.mergeFinalWords(command, 1);
		String itemId = itemName.replaceAll("\\s","");
		Item item = currentRoom.inventory.getItem(itemId);

		if (item != null) {

			inventory.addToInventory(item, item.getAmount());
			currentRoom.inventory.removeItem(itemId);

			if (item.isConsumable()) {
				int amount = item.getAmount();
				if (amount > 1)
					System.out.println("You picked up " + amount + " " + item.getName() + "s.");
				else
					System.out.println("You picked up " + amount + " " + item.getName());
			} else {
				System.out.println("You picked up a " + item.getName());
			}
		} else {
			System.out.println("The room has no " + itemName + "s");
		}
	}

	/*
	 * Equips item
	 */
	private void equipItem(Command command) {
		if (!command.hasManyWords(2)) {
			System.out.println("Equip what?");
			return;
		}

		String itemName = Command.mergeFinalWords(command, 1);
		String itemId = itemName.replaceAll("\\s","");
		Item item = inventory.getItem(itemId);

		if (item != null) {
			if (item instanceof Weapon) {
				
				// Removes item from inventory
				inventory.removeItem(itemId);
				
				// Ensure BareHands is not added to inventory
				if (!(equippedWeapon instanceof BareHands))
					inventory.addToInventory(equippedWeapon, 1);
				
				// Equips weapon
				equippedWeapon = (Weapon) item;
				System.out.println("You equipped " + equippedWeapon);
				
			} else if (item instanceof Armor) {
				
				// Removes item from inventory
				inventory.removeItem(itemId);
				
				// Makes sure armor is equipped before returning it to inventory
				if (equippedArmor != null)
					inventory.addToInventory(equippedArmor, 1);
				
				// Equips armor
				equippedArmor = (Armor) item;
				System.out.println("You equipped " + equippedArmor);
				
			} else {
				System.out.println("You cannot equip that");
			}
		} else {
			System.out.println("You have no " + itemName + "s");
		}
	}

	/*
	 * Unequips a weapon or armor
	 */
	private void unequip(Command command) {
		if (!command.hasManyWords(2)) {
			System.out.println("Unequip what?");
			return;
		}

		String secondWord = command.getSecondWord();

		if ((secondWord.equals("armor") && equippedArmor != null)
				|| (equippedArmor != null && Command.mergeFinalWords(command, 1).equals(equippedArmor.getId()))) {
			inventory.addToInventory(equippedArmor, 1);
			equippedArmor = null;
			System.out.println("You unequiped your " + secondWord);
		} else if ((secondWord.equals("weapon") && equippedWeapon != null)
				|| Command.mergeFinalWords(command, 1).equals(equippedWeapon.getId())) {
			if (!(equippedWeapon instanceof BareHands))
				inventory.addToInventory(equippedWeapon, 1);
			equippedWeapon = new BareHands();
			System.out.println("You unequiped your " + secondWord);
		} else {
			System.out.println(
					secondWord.substring(0, 1).toUpperCase() + secondWord.substring(1) + " is not currently equiped");
		}
	}
	
	/*
	 * Talks to NPC
	 */
	private void talk(Command command) {
		if (!command.hasManyWords(2)) {
			// if there is no second word, we don't know where to go...
			System.out.println("Talk to whom?");
			return;
		}
		if (!currentRoom.entities.hasNPC()) {
			System.out.println("You are alone");
			return;
		}

		String npcName = Command.mergeFinalWords(command, 1);
		String npcId = npcName.replaceAll("\\s","");
		ArrayList<NPC> npcs = currentRoom.entities.getNPCs();
		boolean didTalk = false;
		for (NPC npc : npcs) {
			if (npc.getId().equals(npcId)) {
				System.out.println(npc.getResponse());
				didTalk = true;
			}
		}
		
		if (!didTalk) {
			System.out.println(npcName + " is not here");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String ret = "Your health: " + health + "";
		if (equippedArmor != null) {
			ret += "\nArmor: " + equippedArmor + "\n Shield Points: " + equippedArmor.getShieldPoints();
		}
		if (isPoisoned()) {
			ret += "\nPoison: " + getPoisonDamage();
		}
		return ret;
	}
	
	/*
	 * Normal damage (hits armor before health)
	 */
	public void hit(int damage) {
		if (equippedArmor != null && equippedArmor.getShieldPoints() > 0) {
			int unblockedDamage = equippedArmor.getShieldPoints() - damage;
			equippedArmor.damage(damage, this);
			if (equippedArmor.getShieldPoints() <= 0) {
				equippedArmor = null;
				damage(unblockedDamage);
				System.out.println("Your " + equippedArmor.getName() + " got annihilated.");
			}
		} else {
			damage(damage);
		}
	}
	
	/*
	 * Getters
	 */
	public int getArmorStrength() {
		return this.equippedArmor.getShieldPoints();
	}
	public Weapon getWeapon() {
		return equippedWeapon;
	}
	public int getDamageScaler() {
		return damageScaler;
	}
	
	/*
	 * Setters
	 */
	public void setDamageScaler(int scaler) {
		damageScaler = scaler;
	}

	/*
	 * Attacks
	 */
	public void attack(Entity e) {
		equippedWeapon.ability(e, this);
	}
}
