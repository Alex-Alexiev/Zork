package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Monster;

public class PlagueRat extends Monster {

	private final int POISON_INC = 20;
	private final int POISON_LENGTH = 3;

	public PlagueRat() {
		super("Plague Rat", "A giant rat, with rotten flesh, \nexposed ribcage and visible viscera", 10, 60, 350);
		canGivePoison = true;
	}
	
	public Poison getPoison() {
		return new Poison(POISON_INC, POISON_LENGTH);
	}

}
