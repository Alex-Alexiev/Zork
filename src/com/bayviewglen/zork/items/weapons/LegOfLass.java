package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class LegOfLass extends Weapon{

	public LegOfLass() {
		super("LegOfLass", 30, "Who knew the legs of some Scottish lady would make such fantastic bow arms? Such flexibility! Such Finesse!");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		monster.setHealth(monster.getHealth() - getDamage());
	}

}
