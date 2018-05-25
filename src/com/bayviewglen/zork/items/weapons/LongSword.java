package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.player.Player;

public class LongSword extends Weapon{

	public LongSword() {
		super("LongSword", "The preferred weapon of warriors.", 300, 100);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		if (didHit())
			monster.setHealth(monster.getHealth() - getDamage() - criticalHit());
	}

}
