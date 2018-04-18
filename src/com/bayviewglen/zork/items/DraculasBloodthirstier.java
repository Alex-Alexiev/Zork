package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class DraculasBloodthirstier extends Weapons{

	public DraculasBloodthirstier() {
		super("Dracula’s Bloodthirstier", 40, "Forged by the famous Lord Dracula. "
				+ "This version is an improvement upon his earlier sword: The Bloodthirster.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
