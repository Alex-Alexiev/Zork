package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class RavingLunatic extends Monster {

	public RavingLunatic() {
		super("Raving Lunatic",
				"A person driven to murder from the influence of the island.  \nBrandishes a massive knife, caked with blood.",
				150, 100, 400);
	}

public void attack(Entity e) {
		
		if (Math.random() < (getHitOdds()/100.0)) {
			((Player) e).hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			
		} else {
			System.out.println(this.getName() + " has missed the hit!");
		}
		
	}
}