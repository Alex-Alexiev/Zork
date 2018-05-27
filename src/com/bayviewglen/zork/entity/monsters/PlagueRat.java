package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class PlagueRat extends Monster {

	private final int POISON_INC = 20;
	private final int POISON_LENGTH = 3;

	public PlagueRat() {
		super("Plague Rat", "A giant rat, with rotten flesh, \nexposed ribcage and visible viscera", 10, 60, 350);
	}
	
	public Poison getPoison() {
		return new Poison(POISON_INC, POISON_LENGTH);
	}
	
	public void attack(Entity e) {
		
		int initHealth = e.getHealth();

		if (Math.random() < (getHitOdds()/100.0)) {
			((Player) e).hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			if (initHealth > e.getHealth()) {
				e.addPoison(getPoison());
			}
		} else {
			System.out.println(getId() + " has missed the hit!");
		}
	}

}
