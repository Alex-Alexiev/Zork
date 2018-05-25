package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.player.Player;
import com.bayviewglen.zork.player.Poison;

public class Lucille extends Weapon{
	
	private final int POISON_INC = 10;
	private int POISON_LENGTH = 4;

	public Lucille() {
		super("Lucille", "Barbed wire baseball bat. Highly effective against asians and zombies.", 200, 80);
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){
		if (didHit()) {
			monster.setHealth(monster.getHealth() - getDamage() - criticalHit());
			monster.poisoned(new Poison(POISON_INC, new int[POISON_LENGTH]));
			
			int stun = (int) (Math.random() * 2);
			if (stun == 0) {
				monster.stun(true);
			}
		}
	}
}
