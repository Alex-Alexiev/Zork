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
		
		int initHealth = e.getHealth();

		if (Math.random() < (getHitOdds()/100.0)) {
			((Player) e).hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			if (initHealth > e.getHealth()) {
				//monster gains half the health that the opponent lost from this hit
				int lifesteal = (initHealth - e.getHealth()) / 2;
				setHealth(getHealth() + lifesteal);
				System.out.println(this.getName() + " stole " + lifesteal + " life points");
			}
		} else {
			System.out.println(this.getName() + " has missed the hit!");
		}
		
	}
}