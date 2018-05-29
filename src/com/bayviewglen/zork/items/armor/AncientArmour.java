package com.bayviewglen.zork.items.armor;

import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Armor;

public class AncientArmour extends Armor {
	
	int turns = 0;
	int startSheildPoints = 300;
	
	public AncientArmour() {
		super("Ancient Armour of Antiquities", 300, "Enchanted by the wizard Quintin Spieltoro, this armour has properties which allow it to still be usable after 800 years.");
	}

	public int ability(int damage, Player player) {
		turns++;
		if (turns >= 4) {
			turns = 0;
			setShieldPoints(startSheildPoints);
			System.out.println("Ancient Armour of Antiquities has regained its sheild points");
		}
		return damage;
	}
}
