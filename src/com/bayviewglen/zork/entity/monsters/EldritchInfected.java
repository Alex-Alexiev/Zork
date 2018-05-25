package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.player.Player;

public class EldritchInfected extends Monster {

	public EldritchInfected() {
		super("Eldritch Infected",
				"Seems humanoid, albeit slightly emancipated, \nuntil the mouth opens unnaturally wide, to extend feeding apparatus, <br>consisting of 3 tentacles, lined with sharp teeth",
				100, 850);
	}

	public int ability(Player player) {
		int num = (int) (Math.random() * 8);
		int initHealth = player.getHealth();

		if (num > 6) {
			player.normalDamage(getDamage());
			if (initHealth > player.getHealth()) {
				setHealth(getHealth() + (initHealth - player.getHealth()) / 2);
			} else {
				System.out.println("Eldritch Infected has missed the hit!");
			}
		}

		return initHealth - player.getHealth();
	}
}