package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.player.Player;

public class DesecratedCorpse extends Monster {

	public DesecratedCorpse() {
		super("DesecratedCorpse", "A rotten corpse, with its flesh <br>flayed to emulate twisted cult symbols", 55,
				550);
	}

	public int ability(Player player) {
		int num = (int) (Math.random() * 6);
		int initHealth = player.getHealth();
		if (num < 4) {
			player.normalDamage(getDamage());
		}else{
			System.out.println("Desecrated Corpse has missed the hit!");
		}
		return initHealth-player.getHealth();
	}
}
