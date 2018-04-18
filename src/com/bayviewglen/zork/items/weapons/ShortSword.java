package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.monsters.Monster;
import com.bayviewglen.zork.items.Weapons;

public class ShortSword extends Weapons{

	public ShortSword() {
		super("ShortSword", 15, "Medium sized weapon, excellent for close engagement");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
