package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

public class RavingLunatic extends Monster {

	public RavingLunatic() {
		super("RavingLunatic",
				"A person driven to murder from the influence of the island. <br>Brandishes a massive knife, caked with blood.",
				150, 400);
	}

	public int ability(Player player) {
		int num = (int) (Math.random() *8);
		int initHealth = player.getHealth();
		if (num < 5) {
			player.normalDamage(getDamage()); 
		}
		return initHealth-player.getHealth();
	}
}