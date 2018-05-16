
package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.weapons.Bow;

public class Combat {
	
	private ArrayList<Monster> monsters;
	private Player player;
	
	public Combat(Player player, ArrayList<Monster> monsters) {
		this.monsters = monsters;
		this.player = player;
	}
	
	public boolean chooseEngage() {
		System.out.println("There are some monsters guarding this room:");
		for (Monster m : monsters) {
			System.out.println(m);
		}
		System.out.println();
		System.out.println("Would you like to fight ALL these monsters or walk away?\n");
		String response = Parser.getCommand().getCommandWord();
		if (response.equals("yes") || response.equals("fight")){
			return true;
		} else {
			System.out.println("\nYou return to the previous room");
		}
		return false;
	}
	
	public boolean engageInCombat() {
		System.out.println("\nGood luck soldier, not many people make it out alive.\n");
		if (Math.random() >= 0.5 && !(player.getWeapon() instanceof Bow)) {
			monstersAttack();
		}
		while(monsters.size() > 0 && player.getHealth() > 0) {
			printStats();
			if (!playerAttack()) {
				System.out.println("Back to the previous room\n");
				return false;
			}
			monstersAttack();
			removeDeadMonsters();
		}
		if (monsters.size() <= 0) {
			System.out.println("You have beat the monsters! You can advance to the next room.\n");
			return true;
		}
		else {
			System.out.println("Ops, try again next time...");
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
		for (Monster m : monsters) {
			System.out.println(m.getId()+ " health: " + m.getHealth());
		}
		System.out.println();
	}
	
	private boolean playerAttack() {
		System.out.println("Which monster would you like to attack? Or would you like to walk away?\n");
		Command playerCommand = Parser.getCommand();
		if (playerCommand.numOfWords() < 2) {
			System.out.println("Attack what?");
			return true;
		}
		if (playerCommand.getWordAtIndex(0).toLowerCase().equals("walk")) {
			System.out.println("\nwimp\n");
			return false;
		}
		String monsterId = playerCommand.getSecondWord();
		for (Monster m : monsters) {
			if (m.getId().equals(monsterId)) {
				player.getWeapon().ability(m, player);
				break;
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
