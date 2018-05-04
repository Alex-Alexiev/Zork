package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

public class EldritchInfected extends Monster {

	public EldritchInfected() {
		super("EldritchInfected",
				"Seems humanoid, albeit slightly emancipated, <br>until the mouth opens unnaturally wide, to extend feeding apparatus, <br>consisting of 3 tentacles, lined with sharp teeth",
				100, 850);
	}

	public int ability(Player player) {
		int num = (int) (Math.random() * 8);
		int initHealth = player.getHealth();

		if (num > 6) {
			player.normalDamage(damage);
			if (initHealth > player.getHealth()) {
				health += ((initHealth - player.getHealth()) / 2);

			}
		}
		
		return initHealth-player.getHealth();
	}
}