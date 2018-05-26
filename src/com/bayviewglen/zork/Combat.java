
package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.items.weapons.Bow;
import com.bayviewglen.zork.items.weapons.LegsOfLass;
import com.bayviewglen.zork.player.Player;

public class Combat {
	
	private ArrayList<Monster> monsters;
	private Player player;
	
	public Combat(Player player, ArrayList<Monster> monsters) {
		this.monsters = monsters;
		this.player = player;
	}
	
	public boolean chooseEngage() {
		System.out.println("Monsters guard the room:");
		for (Monster m : monsters) {
			System.out.println(m);
		}
		System.out.println();
		System.out.println("Would you like to fight these monsters or walk away?\n");
		String response = Parser.getCommand().getCommandWord();
		if (response.equals("yes") || response.equals("fight")){
			return true;
		} else {
			System.out.println("\nYou return to the previous room");
		}
		return false;
	}
	
	public boolean engageInCombat() {
		if (Math.random() >= 0.5 && !(player.getWeapon() instanceof Bow)) {
			monstersAttack();
		}
		while(monsters.size() > 0 && player.getHealth() > 0) {
			printStats();
			if (!playerAttack()) {
				System.out.println("Back to the previous room\n");
				return false;
			}
			removeDeadMonsters();
			monstersAttack();
		}
		if (monsters.size() <= 0) {
			System.out.println("The monsters are slain\n");
			return true;
		}
		else {
			System.out.println("Oops, try again next time...");
		}
		return false;
	}
	
	private void monstersAttack() {
		for (Monster m : monsters) {
			if (!m.isStunned()) {
				m.ability(player);
			}
		}
		System.out.println();
	}
	
	private void printStats() {
		System.out.println(player);
		System.out.println();
		for (Monster m : monsters) {
			System.out.println(m.getName()+ " health: " + m.getHealth());
		}
		System.out.println();
	}
	
	private boolean playerAttack() {
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
		
		Weapon weapon = player.getWeapon();
		
		if (weapon instanceof LegsOfLass) {
			int monstersLength = monsters.size();
			for (Monster m : monsters) {
				System.out.println("You attack " + m.getName() + " with " + weapon.getName() + " (-" + weapon.getDamage() + ")");
				((LegsOfLass) weapon).ability(m, monstersLength);
			}
			return true;
		}
		
		String monsterName = Command.mergeFinalWords(playerCommand, 1);
		String monsterId = monsterName.replaceAll("\\s+","");
		
		for (Monster m : monsters) {
			if (m.getId().equals(monsterId)) {
				System.out.println("You attack " + m.getName() + " with " + weapon.getName() + " (-" + weapon.getDamage() + ")");
				weapon.ability(m, player);
				return true;
			}
		}
		return true;
	}
	
	private void removeDeadMonsters() {
		for (int i = 0; i < monsters.size(); i++) {
			if (monsters.get(i).getHealth() <= 0) {
				monsters.remove(i);
				i--;
			}
		}
	}
	
}
