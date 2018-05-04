package com.bayviewglen.zork.items.weapons;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class BareHands extends Weapon{
	
	public BareHands() {
		super("Hands", 0, "You will always die when you fight with your hands...");
	}

	@Override
	public void ability(Monster monster) {
		monster.setHealth(monster.getHealth()-damage);
	}
}
