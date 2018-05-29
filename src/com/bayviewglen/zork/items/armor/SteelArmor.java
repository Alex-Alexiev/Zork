package com.bayviewglen.zork.items.armor;

import com.bayviewglen.zork.items.Armor;
import com.bayviewglen.zork.entity.Player;

public class SteelArmor extends Armor {
	public SteelArmor() {
		super("Steel Armor", 600, "This stuff is the best you can get.");
	}

	public int ability(int damage, Player player) {
		return damage/2;
	}
}
