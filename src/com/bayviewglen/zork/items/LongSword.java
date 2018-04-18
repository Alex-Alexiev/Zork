package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class LongSword extends Weapons{

	public LongSword() {
		super("LongSword", 30, "The preferred weapon of warriors.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
