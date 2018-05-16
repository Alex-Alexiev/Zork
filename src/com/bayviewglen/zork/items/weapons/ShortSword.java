package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class ShortSword extends Weapon{

	public ShortSword() {
		super("ShortSword", 15, "Medium sized weapon, excellent for close engagement");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		monster.setHealth(monster.getHealth() - getDamage());
	}

}
