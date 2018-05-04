package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Monster;

public class Combat {
	
	private ArrayList<Monster> monsters;
	private Player player;
	
	public Combat(Player player, ArrayList<Monster> monsters) {
		this.monsters = monsters;
		this.player = player;
	}
	
	public boolean chooseEngage() {
		System.out.println("Here are the monsters gaurding this room: ");
		for (Monster m : monsters) {
			System.out.println(m);
		}
		System.out.println();
		System.out.println("Would you like to fight ALL these monsters (and get destroyed) or walk away? (loser)");
		if (Parser.getCommand().getCommandWord().toLowerCase().equals("yes")){
			engageInCombat();
		}
		return false;
	}
	
	public void engageInCombat() {
		System.out.println("You made the wrong choice");
		if (Math.random() >= 0.5) {
			monstersAttack();
		}
		while(monsters.size() > 0 && player.getHealth() > 0) {
			printStats();
			playerAttack();
			monstersAttack();
		}
	}
	
	private void monstersAttack() {
		for (Monster m : monsters) {
			m.ability(player);
			System.out.println();
		}
	}
	
	private void printStats() {
		System.out.println("Your health: "+ player.getHealth());
		for (Monster m : monsters) {
			System.out.println(m.getId()+ " health: "+m.getHealth());
		}
	}
	
	private void playerAttack() {
		System.out.println("Which monster would you like to attack?");
		Command playerCommand = Parser.getCommand();
		String monsterId = playerCommand.getSecondWord();
		for (Monster m : monsters) {
			if (m.getId().equals(monsterId)) {
				player.getWeapon().ability(m);
				break;
			}
		}
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
