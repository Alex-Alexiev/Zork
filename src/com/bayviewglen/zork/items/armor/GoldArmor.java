package com.bayviewglen.zork.items.armor;

import com.bayviewglen.zork.items.Armor;
import com.bayviewglen.zork.entity.Player;

public class GoldArmor extends Armor {
	public GoldArmor() {
		super("Gold Armor", 400, "Looks good for the ladies. Too bad gold armor sucks...");
	}

	public int ability(int damage, Player player) {
		return damage;
	}
}
