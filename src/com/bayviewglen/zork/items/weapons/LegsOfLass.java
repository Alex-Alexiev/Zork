package com.bayviewglen.zork.items.weapons;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Weapon;

public class LegsOfLass extends Weapon {

	public LegsOfLass() {
		super("Legs of Lass",
				"Who knew the legs of some Scottish lady would make such fantastic bow arms? Such flexibility! Such Finesse!",
				300, 60);
		// TODO Auto-generated constructor stub
	}

	public void ability(Entity e, int monsters, Player p) {
		if (didHit()) {
			int dam = (int)(getDamage() * p.getDamageScaler() + criticalHit());
			e.setHealth(e.getHealth() - dam);
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + dam + ")");
		}
	}
}
