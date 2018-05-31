package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class HeartOfTheIsland extends Monster {

	public HeartOfTheIsland() {
		super("Heart Of The Island", "This will be your final battle. It sits at the very core of the cave. As it beats, Its tainted blood spurts onto the ground and is soaked up by the soil. It cries blood and echoes of infants crying can be heard when it is struck. There are bodies of people, too weak to make a noise, that are standing around it. All are armed.", 50,
				10, 80000);
	}
	
	public void attack(Entity e) {
		
		Player player = (Player) e;
		
		if (Math.random() < (getHitOdds() / 100.0)) {
			player.hit(getDamage());
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			if (!player.hasRabies()) {
				player.setDamageScaler(player.getDamageScaler() * 0.97);
				Poison p = new Poison(0, -1);
				p.setFinalAction(true);
				p.setId("rabies");
				e.addPoison(p);
			}
		}else{
			System.out.println(getId() + " has missed the hit!");
		}
	}
}
