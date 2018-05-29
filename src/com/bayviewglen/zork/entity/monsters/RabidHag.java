package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class RabidHag extends Monster {
	
	public RabidHag() {
		super("Rabid Hag", "Old filthy elderly woman, wearing clothing made of human skin. Her earrings and necklace are derived from human parts. (Fingers, nose, ears, eyeballs, etc.)\r\n" + 
				"Salivating greatly, she looks hungry.", 30,50, 300);
	}
public void attack(Entity e) {
		
		int initHealth = e.getHealth();

		if (Math.random() < (getHitOdds()/100.0)) {
			((Player) e).hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			
		} else {
			System.out.println(this.getName() + " has missed the hit!");
		}
		
	}
	
}
