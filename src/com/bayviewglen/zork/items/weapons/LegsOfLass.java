package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.player.Player;

public class LegsOfLass extends Weapon{

	public LegsOfLass() {
		super("Legs of Lass", "Who knew the legs of some Scottish lady would make such fantastic bow arms? Such flexibility! Such Finesse!", 300, 60);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, int monsters) {
		if (didHit())
			monster.setHealth(monster.getHealth() - getDamage()/monsters);
	}
	
	public void ability(Monster monster, Player player){
		if (didHit())
			monster.setHealth(monster.getHealth() - getDamage() - criticalHit());
	}

}
