package com.bayviewglen.zork;
import java.util.concurrent.TimeUnit;

import com.bayviewglen.zork.items.Food;
import com.bayviewglen.zork.items.Item;

public class Player {
	
	private Room currentRoom;
	private Parser parser;
	private Inventory inventory;
	
	private int health;
	private int maxHealth;
		
	public Player(Room startingRoom) {
		currentRoom = startingRoom;
		parser = new Parser();
		inventory = new Inventory();
		maxHealth = 100;
		health = 90;
	}
	
	/**
     * Parse command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public boolean act() 
    {
    	Command command = parser.getCommand();
    	
    	System.out.println();
    	    	
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("room"))
        	printLocation();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("where"))
        	printLocation();
        else if (commandWord.equals("inventory"))
        	printInventory();
        else if (commandWord.equals("quit"))
        {
            if (command.hasManyWords(2))
                System.out.println("Quit what?");
            else
                return true;  // signal that we want to quit
        }
        else if (commandWord.equals("eat")){
        	eat(command);
        }
        else if (commandWord.equals("take")) {
        	pickupItem(command);
        }
        else if (commandWord.equals("attack")) {
        	attack(command);
        }
        else if (commandWord.equals("drop")) {
        	dropItem(command);
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
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at Monash Uni, Peninsula Campus.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * Prints inventory
     */
    private void printInventory() {
    	System.out.println("You have:" + inventory);
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasManyWords(2))
        {
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
	 * @param direction
	 */
	private void move(String direction) {
		Room nextRoom = currentRoom.nextRoom(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else 
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.longDescription());
        }
	}
	
	/**
	 * Eats food
	 */
	private void eat(Command command) {
		if(!command.hasManyWords(2))
        {
            System.out.println("Eat what?");
            return;
        }
		
		String foodId = command.getSecondWord();
        Food food = (Food) inventory.getItem(foodId);
        
        if (food != null) {
        	
        	if (health >= maxHealth) {
        		System.out.println("You are full");
        		return;
        	}
        	
        	for (int i = 0; i < 3; i++) {
        		System.out.print("*munch*");
        		try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        	
        	int foodHealthPoint = food.getHealthPoints();
        	health += foodHealthPoint;
        	inventory.reduceAmount(foodId, 1);
        	
        	if (health > maxHealth)
        		health = maxHealth;
        	        	        	
        	System.out.print(" Yum\n");
        	System.out.println("Health: " + health);
        	
        } else {
        	System.out.println("You have no " + foodId + "s");
        }
	}
	
	/**
	 * Drops an item
	 * @param command
	 */
	private void dropItem(Command command) {
		if(!command.hasManyWords(2))
        {
            System.out.println("Drop what?");
            return;
        }
		
        String itemId = command.getSecondWord();
        Item item = inventory.getItem(itemId);
        
        if (item != null) {
        	
        	if (item.isConsumable()) {
        		int amount = item.getAmount();
        		if (amount > 1)
        			System.out.println("You dropped " + amount + " " + itemId + "s.");
        		else 
        			System.out.println("You dropped " + amount + " " + itemId);
        	} else {
        		System.out.println("You dropped your" + itemId);
        	}
        	currentRoom.inventory.addToInventory(item, item.getAmount());
        	inventory.removeItem(itemId);
        } else {
        	System.out.println("You have no " + itemId + "s");
        }
        
        System.out.println("\nThe room has " + currentRoom.inventory);
        
	}
	
	/**
	 * Picks up an item
	 * @param command
	 */
	private void pickupItem(Command command) {
		if(!command.hasManyWords(2))
        {
            System.out.println("Pickup what?");
            return;
        }
		
        String itemId = command.getSecondWord();
        Item item = currentRoom.inventory.getItem(itemId);
        
        if (item != null) {
        	
        	inventory.addToInventory(item, item.getAmount());
        	currentRoom.inventory.removeItem(itemId);
        	
        	if (item.isConsumable()) {
        		int amount = item.getAmount();
        		if (amount > 1)
        			System.out.println("You picked up " + amount + " " + itemId + "s.");
        		else 
        			System.out.println("You picked up " + amount + " " + itemId);
        	} else {
        		System.out.println("You picked up a " + itemId);
        	}
        } else {
        	System.out.println("The room has no " + itemId + "s");
        }
        
        System.out.println("\nThe room has " + currentRoom.inventory);
	}
	
	/*
	 * Attacks monster
	 */
	private void attack(Command command) {
		if(!command.hasManyWords(2))
        {
			if(!command.hasManyWords(4))
	        {
	            System.out.println("Attack monster with what?");
	            return;
	        }
			System.out.println("Attack what?");
            return;
        }
		
		String monsterId = command.getSecondWord();
	}
}