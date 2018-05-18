package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class CleaverOfGiants extends Weapon{

	public CleaverOfGiants() {
		super("CleaverOfGiants", "Not just for cutting the beanstalk.", 0, 50);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		if (didHit())
			monster.setHealth(monster.getHealth() - monster.getHealth()/2);
	}

}
