package com.bayviewglen.zork.items.weapons;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class BareHands extends Weapon{
	
	public BareHands() {
		super("Hands", 100, "You will always die when you fight with your hands...");
	}

	public void ability(Monster monster, Player player) {
		System.out.println("You attack "+monster.getId()+" with "+getId()+" (-"+ getDamage()+")\n");
		monster.setHealth(monster.getHealth()-getDamage());
	}
}
