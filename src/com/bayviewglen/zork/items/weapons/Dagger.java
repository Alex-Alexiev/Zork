package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.monsters.Monster;
import com.bayviewglen.zork.items.Weapons;

public class Dagger extends Weapons{

	public Dagger() {
		super("dagger", 10, "Stabby time!");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
