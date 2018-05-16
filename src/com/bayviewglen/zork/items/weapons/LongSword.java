package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class LongSword extends Weapon{

	public LongSword() {
		super("LongSword", 30, "The preferred weapon of warriors.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		monster.setHealth(monster.getHealth() - getDamage());
	}

}
