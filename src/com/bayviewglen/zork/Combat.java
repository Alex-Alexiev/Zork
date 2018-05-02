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
	
	public void engageInCombat() {
		if (Math.random() >= 0.5) {
			monstersAttack();
		}
		while(monsters.size() > 0 && player.getHealth() > 0) {
			playerAttack();
			monstersAttack();
		}
	}
	
	private void monstersAttack() {
		for (Monster m : monsters) {
			m.ability(player);
		}
	}
	
	private void playerAttack() {
		Command playerCommand = Parser.getCommand();
		String monsterId = playerCommand.getSecondWord();
		Monster selectedMonster;
		for (Monster m : monsters) {
			if (m.getId().equals(monsterId)) {
				selectedMonster = m;
				break;
			}
		}
		player.getWeapon().ability(selectedMonster);
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
