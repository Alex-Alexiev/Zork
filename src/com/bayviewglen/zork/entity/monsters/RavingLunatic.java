package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.player.Player;

public class RavingLunatic extends Monster {

	public RavingLunatic() {
		super("Raving Lunatic",
				"A person driven to murder from the influence of the island.  \nBrandishes a massive knife, caked with blood.",
				150, 400);
	}

	public int ability(Player player) {
		int num = (int) (Math.random() *8);
		int initHealth = player.getHealth();
		if (num < 5) {
			player.normalDamage(getDamage()); 
		}else{
			System.out.println("Raving Lunatic has missed the hit!");
		}
		return initHealth-player.getHealth();
	}
}