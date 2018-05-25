package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.player.Player;

public class Bow extends Weapon{

	public Bow() {
		super("Bow", "Now you can fight, WITHOUT having to fight!", 75, 50);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		if (didHit())
			monster.setHealth(monster.getHealth() - getDamage() - criticalHit());
	}

}
