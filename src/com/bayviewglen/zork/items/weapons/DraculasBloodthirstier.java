package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class DraculasBloodthirstier extends Weapon{

	private int specialDamage = 30;
	
	public DraculasBloodthirstier() {
		super("Dracula’s Bloodthirstier", 40, "Forged by the famous Lord Dracula. "
				+ "This version is an improvement upon his earlier sword: The Bloodthirster.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		int chance = (int) (Math.random() *7);
		
		player.specialDamage(specialDamage);
		
		if (chance > 4) {
			monster.setHealth(monster.getHealth() - getDamage());
			player.affectHealth(-getDamage());
		}
		else {
			System.out.println("You have missed the hit!");
		}
	}

}
