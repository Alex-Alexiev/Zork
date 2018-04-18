package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.monsters.Monster;
import com.bayviewglen.zork.items.Weapons;

public class Bow extends Weapons{

	public Bow() {
		super("Bow", 8, "Now you can fight, WITHOUT having to fight!");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
