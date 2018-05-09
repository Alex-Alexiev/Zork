package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

public class PlagueRat extends Monster {
	
	private int poisDmg = 0;
	private final int poisDmgInc = 10;
	private int poisLength = 4;
	private int[] poisStacks = new int[poisLength];
	
	public PlagueRat() {
		super("PlagueRat", "A giant rat, with rotten flesh, <br>exposed ribcage and visible viscera", 10, 350);
	}
	
	public int ability(Player player) {
		int num = (int) (Math.random() *7);
		int initHealth = player.getHealth();
		
		if (num <= 5) {
			player.normalDamage(getDamage()); 
		
			if (player.getHealth() < initHealth) {
				
				poisDmg += poisDmgInc;
				for (int i = 0; i < poisStacks.length; i++) {
					if (poisStacks[i] == 0) {
						poisStacks[i] = poisLength;
					}
				}
			}
		}
		
		for (int i = 0; i < poisStacks.length; i++) {
			if (poisStacks[i] > 0) {
				poisStacks[i] -= 1;
			}
			
			if (poisStacks[i] == 0) {
				poisDmg -= poisDmgInc; 
			}
		}
		
		if (poisDmg > 0) {
			player.specialDamage(poisDmg);
		
		}
		return initHealth-player.getHealth();
	}

}
