package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class CleaverOfGiants extends Weapon{

	public CleaverOfGiants() {
		super("CleaverOfGiants", 30, "Not just for cutting the beanstalk.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - monster.getHealth()/2);
	}

}
