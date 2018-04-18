package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class Dagger extends Weapons{

	public Dagger() {
		super("dagger", 10, "Stabby time!");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
