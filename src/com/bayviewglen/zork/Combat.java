
package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.items.weapons.Bow;
import com.bayviewglen.zork.items.weapons.LegsOfLass;

public class Combat {

	private ArrayList<Entity> enemies;
	private Player player;

	public Combat(Player player, ArrayList<Entity> enemies) {
		this.enemies = enemies;
		this.player = player;
	}

	public boolean chooseEngage() {
		System.out.println("Monsters guard the room:");
		for (Entity e : enemies) {
			System.out.println(e);
		}
		System.out.println();
		System.out.println("Would you like to fight these monsters or walk away?\n");
		String response = Parser.getCommand().getCommandWord();
		if (response.equals("yes") || response.equals("fight")) {
			return true;
		} else {
			System.out.println("\nYou return to the previous room");
		}
		System.out.println();
		
		return false;
	}

	public boolean engageInCombat() {
		if (Math.random() >= 0.5 && !(player.getWeapon() instanceof Bow)) {
			enemiesAttack();
		}
		
		while (enemies.size() > 0 && player.getHealth() > 0) {
			printStats();
			if (!playerAttack()) {
				System.out.println("Back to the previous room\n");
				return false;
			}
			removeDeadEnemies();
			enemiesAttack();
		}
		if (enemies.size() <= 0) {
			System.out.println("The monsters are slain\n");
			return true;
		} else {
			System.out.println("Oops, try again next time...");
		}
		return false;
	}

	/*
	 * Enemies attack
	 */
	private void enemiesAttack() {
		for (Entity e : enemies) {
			// Damages monsters with poison
			e.poison();
			
			// Attacks player
			if (!(e instanceof Monster && ((Monster) e).isStunned())) {
				e.attack(player);
			}
		}
		System.out.println();
	}

	/*
	 * Prints the stats of the player and all monsters
	 */
	private void printStats() {
		System.out.println(player);
		System.out.println();
		for (Entity e : enemies) {
			System.out.println(e.getName() + " health: " + e.getHealth());
		}
		System.out.println();
	}

	/*
	 * Player attack
	 */
	private boolean playerAttack() {
		
		// Damages player with poison
		player.poison();
		
		// Prompts player
		System.out.println("Which monster would you like to attack? Or would you like to walk away?\n");
		Command playerCommand = Parser.getCommand();
		System.out.println();
		if (playerCommand.numOfWords() < 2) {
			System.out.println("Attack what?");
			return true;
		}
		if (playerCommand.getWordAtIndex(0).toLowerCase().equals("walk")) {
			System.out.println("\nWimp\n");
			return false;
		}

		// Legs of Lass special case
		Weapon weapon = player.getWeapon();
		if (weapon instanceof LegsOfLass) {
			int monstersLength = enemies.size();
			for (Entity e : enemies) {
				System.out.println(
						"You attack " + e.getName() + " with " + weapon.getName() + " (-" + weapon.getDamage() + ")");
				((LegsOfLass) weapon).ability(e, monstersLength);
			}
			return true;
		}

		// Normal attack
		String monsterName = Command.mergeFinalWords(playerCommand, 1);
		String monsterId = monsterName.replaceAll("\\s+", "");

		for (Entity e : enemies) {
			if (e.getId().equals(monsterId)) {
				player.attack(e);
				return true;
			}
		}
		
		return true;
	}

	/*
	 * Removes all dead monsters from the list
	 */
	private void removeDeadEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHealth() <= 0) {
				enemies.remove(i);
				i--;
			}
		}
	}

}
