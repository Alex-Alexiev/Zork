package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Poison;
import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Weapon;

public class Lucille extends Weapon{
	
	private final int POISON_INC = 10;
	private int POISON_LENGTH = 4;

	public Lucille() {
		super("Lucille", "Barbed wire baseball bat. Highly effective against asians and zombies.", 200, 80);
	}
	
	public void ability(Entity e, Player p){
		if (didHit()) {
			// Attack
			int dam = (int)(getDamage() * p.getDamageScaler() + criticalHit());
			e.setHealth(e.getHealth() - dam);
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + dam + ")");
			
			// Poison
			e.addPoison(getPoison());
			
			// Stun
			int stun = (int) (Math.random() * 2);
			if (stun == 0) {
				System.out.println(e.getName() + " has been stunned by Lucille");
				e.stun(true);
			}
		}
	}
	
	public Poison getPoison() {
		return new Poison(POISON_INC, POISON_LENGTH);
	}
}
