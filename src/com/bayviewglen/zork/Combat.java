package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Monster;

public class Combat {
	
	private ArrayList<Monster> monsters;
	private Player player;
	private boolean runAway;
	
	public Combat(Player player, ArrayList<Monster> monsters) {
		this.monsters = monsters;
		this.player = player;
	}
	
	public boolean chooseEngage() {
		System.out.println("\nThere are some monsters guarding this room:\n");
		for (Monster m : monsters) {
			System.out.println(m);
		}
		System.out.println();
		System.out.println("Would you like to fight ALL these monsters (and get destroyed) or walk away?");
		if (Parser.getCommand().getCommandWord().toLowerCase().equals("yes")){
			engageInCombat();
		}
		System.out.println("\nI see how it is... good luck making progress because you are still in the same room.\n");
		player.printLocation();
		return false;
	}
	
	public void engageInCombat() {
		System.out.println("\nGood luck soldier, not many people make it out alive.\n");
		if (Math.random() >= 0.5) {
			monstersAttack();
		}
		while(!runAway && monsters.size() > 0 && player.getHealth() > 0) {
			printStats();
			if (!playerAttack()) {
				break;
			}
			monstersAttack();
			removeDeadMonsters();
		}
	}
	
	private void monstersAttack() {
		for (Monster m : monsters) {
			m.ability(player);
			System.out.println(m.getId()+" has attacked you!");
		}
		System.out.println();
	}
	
	private void printStats() {
		System.out.println("Your health: "+ player.getHealth());
		for (Monster m : monsters) {
			System.out.println(m.getId()+ " health: "+m.getHealth());
		}
		System.out.println();
	}
	
	private boolean playerAttack() {
		System.out.println("Which monster would you like to attack? (you can also walk away)");
		Command playerCommand = Parser.getCommand();
		if (playerCommand.getWordAtIndex(0).toLowerCase().equals("walk")) {
			return false;
		}
		String monsterId = playerCommand.getSecondWord();
		for (Monster m : monsters) {
			if (m.getId().equals(monsterId)) {
				player.getWeapon().ability(m);
				System.out.println("\nYou have attacked "+m.getId()+"\n");
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
