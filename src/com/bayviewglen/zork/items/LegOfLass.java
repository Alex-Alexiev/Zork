package com.bayviewglen.zork.items;
import com.bayviewglen.zork.monsters.Monster;

public class LegOfLass extends Weapons{

	public LegOfLass() {
		super("LegOfLass", 30, "Who knew the legs of some Scottish lady would make such fantastic bow arms? Such flexibility! Such Finesse!");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster){
		monster.setHealth(monster.getHealth() - damage);
	}

}
