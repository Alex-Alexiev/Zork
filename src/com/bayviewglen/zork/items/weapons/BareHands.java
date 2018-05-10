package com.bayviewglen.zork.items.weapons;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class BareHands extends Weapon{
	
	public BareHands() {
		super("Hands", 20, "You will always die when you fight with your hands...");
	}

	public void ability(Monster monster) {
		monster.setHealth(monster.getHealth()-getDamage());
	}
}
