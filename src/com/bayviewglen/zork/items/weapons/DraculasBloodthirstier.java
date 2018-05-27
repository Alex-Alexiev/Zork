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
			int damage = getDamage() + criticalHit();
			e.setHealth(e.getHealth() - damage);
			player.damage(damage);
		}
	}

}
