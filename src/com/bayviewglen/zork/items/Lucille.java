package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class Lucille extends Weapons{

	public Lucille() {
		super("Lucille", 20, "Barbed wire baseball bat. Highly effective against asians and zombies.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
