package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

public class BloodPriest extends Monster {
	
	//ask josh b/c I made these values up randomly for testing
	public BloodPriest() {
		super("BloodPriest", "Don't know what this does (ask Josh)", 10, 10);
	}
	
	public BloodPriest(String id, String description, int damage, int health) {
		super(id, description, damage, health);
	}
	
	public void ability(Player player) {
		
	}
}
