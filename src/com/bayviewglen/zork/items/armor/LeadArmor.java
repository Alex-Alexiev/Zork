package com.bayviewglen.zork.items.armor;

import com.bayviewglen.zork.items.Armor;
import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Player;

public class LeadArmor extends Armor {
	
	int turns = 0;
	
	private final int POISON_INC = 20;
	private final int POISON_LENGTH = 3;
	
	public Poison getPoison() {
		Poison ret = new Poison(POISON_INC, POISON_LENGTH);
		ret.setId("poison");
		return ret;
	}
	
	public LeadArmor() {
		super("lead armor", 2000, "\r\n" + "This is very robust. It's also Lead fre… It’s strong and durable!");
	}

	public int ability(int damage, Player player) {
		turns++;
		if (turns == 20) 
			player.addPoison(getPoison());
		return damage;
	}
}
