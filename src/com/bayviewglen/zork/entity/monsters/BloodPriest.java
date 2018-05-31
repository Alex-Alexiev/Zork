package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class BloodPriest extends Monster {
	
	
	
	public BloodPriest() {
		super("Blood Priest", "Hooded Acolyte that is covered in scars. Holds a ceremonial knife, with\nornate carvings, and is spattered with blood.", 30,50, 350);
	}

	public void attack(Entity e) {
		
		Player player = (Player) e;
		
		if (Math.random() < (getHitOdds() / 100.0)) {
			player.hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			if (getHealth() < getPrevHealth()) {
				int damage = getPrevHealth() - getHealth();
				player.damage(damage);
				System.out.println("Gruesome bond allowed" + this.getName() + " to damage you by (-" + damage + ")");
			}
		} else {
			System.out.println(getId() + " has missed the hit!");
		}
	}
	
}
