package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class Dagger extends Weapon{

	public Dagger() {
		super("dagger", 10, "Stabby time!");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		monster.setHealth(monster.getHealth() - getDamage());
	}

}
