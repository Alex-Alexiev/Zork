package com.bayviewglen.zork.items.armor;

import com.bayviewglen.zork.items.Armor;
import com.bayviewglen.zork.entity.Player;

public class BasicArmor extends Armor {
	public BasicArmor() {
		super("Basic Armor", 500, "Basic Silver Armour, left by some unfortunate fool who took it off for too long.");
	}

	public int ability(int damage, Player player) {
		return damage;
	}
}
