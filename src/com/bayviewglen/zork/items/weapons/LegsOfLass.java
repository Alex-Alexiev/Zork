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

	public void ability(Entity e, int monsters, Player player) {
		if (didHit()) {
			e.setHealth(e.getHealth() - getDamage() * player.getDamageScaler() / monsters);
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + getDamage() + ")");
		}
	}
}
