package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class DraculasBloodthirstier extends Weapon{

	public DraculasBloodthirstier() {
		super("Dracula’s Bloodthirstier", 40, "Forged by the famous Lord Dracula. "
				+ "This version is an improvement upon his earlier sword: The Bloodthirster.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - getDamage());
	}

}
