
package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.entity.monsters.SulfuricCrawler;
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
		Command response = Parser.getCommand();
		System.out.println();
		if (response.is("yes") || response.is("fight")) {
			return true;
		} else {
			System.out.println("You return to the previous room");
		}

		return false;
	}

	public boolean engageInCombat() {

		player.combat(true);

		Sound.stop();
		Sound mainThemeMusic = new Sound("data\\battle.wav");
		mainThemeMusic.loop();

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
			restartMainMusic();
			player.setDamageScaler(1);
			System.out.println("The monsters are slain\n");
			player.combat(false);
			return true;
		} else {
			restartMainMusic();
			player.setDamageScaler(1);
			System.out.println("Oops, try again next time...");
			player.combat(false);
		}
		return false;
	}

	private void restartMainMusic() {
		Sound.stop();
		Sound mainThemeMusic = new Sound("data\\mainmusic.wav");
		mainThemeMusic.loop();
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
			if (e.isPoisoned()) {
				System.out.println(e.getName() +" is bleeding for " + e.getPoisonDamage() + "damage per turn");
			}
		}
		System.out.println();
	}

	/*
	 * Player attack
	 */
	private boolean playerAttack() {

		System.out.println("Which monster would you like to attack?");
		System.out.println();
		Command command = Parser.getCommand();
		player.act(command);

		if (command.is("leave")) {
			System.out.println("\nYou barely escaped.\n");
			return false;
		}
		if (command.numOfWords() < 2) {
			System.out.println("Attack what?");
			return true;
		}

		// Legs of Lass special case
		Weapon weapon = player.getWeapon();
		if (weapon instanceof LegsOfLass) {
			int monstersLength = enemies.size();
			for (Entity e : enemies) {
				((LegsOfLass) weapon).ability(e, monstersLength, player);
			}
			return true;
		}

		// Normal attack
		String monsterId = findMonsterId(command);

		if (monsterId == null)
			return true;

		for (Entity e : enemies) {
			if (e.getId().equals(monsterId.trim().toLowerCase())) {
				player.attack(e);
				return true;
			}
		}

		return true;
	}

	private String findMonsterId(Command playerCommand) {
		String merged = Command.mergeFinalWords(playerCommand, 0);
		String[] monsters = CommandWords.getRelatedWords("monsters");
		for (String monster : monsters) {
			if (merged.indexOf(monster.trim()) != -1)
				return monster;
		}
		return null;
	}

	/*
	 * Removes all dead monsters from the list
	 */
	private void removeDeadEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHealth() <= 0) {
				if (enemies.get(i) instanceof SulfuricCrawler) {
					((SulfuricCrawler) enemies.get(i)).deadMove(player);
				}
				enemies.remove(i);
				i--;
			}
		}
	}

}