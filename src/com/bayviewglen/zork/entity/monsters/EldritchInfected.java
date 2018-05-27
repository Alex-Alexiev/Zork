package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class EldritchInfected extends Monster {

	public EldritchInfected() {
		super("Eldritch Infected",
				"Seems humanoid, albeit slightly emancipated, \nuntil the mouth opens unnaturally wide, to extend feeding apparatus, <br>consisting of 3 tentacles, lined with sharp teeth",
				100, 75, 850);
	}

	public void attack(Entity e) {
		int num = (int) (Math.random() * 8);
		int initHealth = e.getHealth();

		if (num > 6) {
			if (e.hasArmor) {
				((Player) e).hit(getDamage());
				if (initHealth > e.getHealth()) {
					//monster gains half the health that the opponent lost from this hit
					setHealth(getHealth() + (initHealth - e.getHealth()) / 2);
				} else {
					System.out.println("Eldritch Infected has missed the hit!");
				}
			}
		}
	}
}