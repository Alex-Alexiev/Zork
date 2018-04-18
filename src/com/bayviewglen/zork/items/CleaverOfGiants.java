package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class CleaverOfGiants extends Weapons{

	public CleaverOfGiants() {
		super("CleaverOfGiants", 30, "Not just for cutting the beanstalk.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - monster.getHealth()/2);
	}

}
