package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.player.Player;

public class DraculasBloodthirstier extends Weapon{

	private int specialDamage = 30;
	
	public DraculasBloodthirstier() {
		super("Dracula’s Bloodthirstier", "Forged by the famous Lord Dracula. "
				+ "This version is an improvement upon his earlier sword: The Bloodthirster.", 400, 50);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		player.specialDamage(specialDamage);
		if (didHit()) {
			int damage = getDamage() + criticalHit();
			monster.setHealth(monster.getHealth() - damage);
			player.affectHealth(-damage);
		}
	}

}
