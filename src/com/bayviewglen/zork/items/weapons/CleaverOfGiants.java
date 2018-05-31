package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Entity;

import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Weapon;

public class CleaverOfGiants extends Weapon{

	public CleaverOfGiants() {
		super("Cleaver of Giants", "Not just for cutting the beanstalk.", 0, 50, 10);
	}
	
	public void ability(Entity e, Player player){
		if (didHit()) {
			int damage = e.getHealth()/2;
			e.setHealth(e.getHealth() - damage);
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + damage + ")");
		}
	}

}
