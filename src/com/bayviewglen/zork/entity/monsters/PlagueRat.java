package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

public class PlagueRat extends Monster {
	
	//once again made up values for testing
	public PlagueRat() {
		super("PlagueRat", "Not sure, Josh help me", 100, 100);
	}
	
	public PlagueRat(String id, String description, int damage, int health) {
		super(id, description, damage, health);
	}
	
	public void ability(Player player) {
		
	}

}
