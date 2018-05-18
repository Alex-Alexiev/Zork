package com.bayviewglen.zork.items.weapons;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class BareHands extends Weapon{
	
	public BareHands() {
		super("Hands", "You will always die when you fight with your hands...", 50, 100);
	}

	public void ability(Monster monster, Player player) {
		if (didHit())
			monster.setHealth(monster.getHealth() - getDamage() - criticalHit());
	}
}
