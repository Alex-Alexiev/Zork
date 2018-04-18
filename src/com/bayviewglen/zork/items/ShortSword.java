package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class ShortSword extends Weapons{

	public ShortSword() {
		super("ShortSword", 15, "Medium sized weapon, excellent for close engagement");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
