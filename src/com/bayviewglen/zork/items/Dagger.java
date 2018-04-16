package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class Dagger extends Weapons{

	public Dagger(String id, int damage) {
		super(id, damage);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
