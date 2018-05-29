package com.bayviewglen.zork.items.weapons;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Weapon;

public class DraculasBloodthirstier extends Weapon {

	private int specialDamage = 30;

	public DraculasBloodthirstier() {
		super("Dracula�s Bloodthirstier", "Forged by the famous Lord Dracula. "
				+ "This version is an improvement upon his earlier sword: The Bloodthirster.", 400, 50);
	}

	public void ability(Entity e, Player player) {
		player.damage(specialDamage);
		if (didHit()) {
			// Attack
			int damage = getDamage() * player.getDamageScaler() + criticalHit();
			e.setHealth(e.getHealth() - damage);
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + getDamage() + ")");
			
			// Heal
			player.setHealth(player.getHealth() + damage);
			System.out.println("You heal for " + damage + " health points");
		}
	}

}
