package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.player.Player;
import com.bayviewglen.zork.player.Poison;

public class PlagueRat extends Monster {
	
	private final int POISON_INC = 20;
	private final int POISON_LENGTH = 3;
	
	public PlagueRat() {
		super("PlagueRat", "A giant rat, with rotten flesh, \nexposed ribcage and visible viscera", 10, 350);
	}
	
	public int ability(Player player) {
		int num = (int) (Math.random() *7);
		int initHealth = player.getHealth();
		
		if (num <= 5) {
			System.out.println(getId() + " has hit you (-"+getDamage()+")");
			player.normalDamage(getDamage()); 
		
			if (player.getHealth() < initHealth) {
				player.poisoned(new Poison(POISON_INC, new int[POISON_LENGTH]));
			}
		}
		else {
			System.out.println("Plague Rat has missed the hit!");
		}
		
		return initHealth-player.getHealth();
	}

}
