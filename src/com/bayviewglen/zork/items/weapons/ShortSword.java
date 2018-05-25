package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.player.Player;

public class ShortSword extends Weapon{

	public ShortSword() {
		super("ShortSword", "Medium sized weapon, excellent for close engagement", 150, 80);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		if (didHit())
			monster.setHealth(monster.getHealth() - getDamage() - criticalHit());
	}

}
