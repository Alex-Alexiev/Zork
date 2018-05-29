package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class DesecratedCorpse extends Monster {

	public DesecratedCorpse() {
		super("Desecrated Corpse", "A rotten corpse, with its flesh <br>flayed to emulate twisted cult symbols", 55,
				70, 550);
	}
public void attack(Entity e) {
		
		int initHealth = e.getHealth();

		if (Math.random() < (getHitOdds()/100.0)) {
			((Player) e).hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			
		} else {
			System.out.println(this.getName() + " has missed the hit!");
		}
		
	}
}
