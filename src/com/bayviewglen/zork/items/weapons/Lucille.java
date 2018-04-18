package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.monsters.Monster;
import com.bayviewglen.zork.items.Weapons;

public class Lucille extends Weapons{

	public Lucille() {
		super("Lucille", 20, "Barbed wire baseball bat. Highly effective against asians and zombies.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
