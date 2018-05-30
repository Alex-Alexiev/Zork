package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class RabidHag extends Monster {

	public RabidHag() {
		super("Rabid Hag",
				"Old filthy elderly woman, wearing clothing made of human skin. Her earrings and necklace are derived from human parts. Salivating greatly, she looks hungry.",
				50, 75, 300);
	}

	public void attack(Entity e) {
		if (Math.random() < (getHitOdds() / 100.0)) {
			((Player) e).hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			if (!e.hasRabies()) {
				((Player)e).setDamageScaler(1.4);
				Poison p = new Poison(250, 3);
				p.setFinalAction(true);
				p.setId("rabies");
				e.addPoison(p);
			}
		}
	}
}
