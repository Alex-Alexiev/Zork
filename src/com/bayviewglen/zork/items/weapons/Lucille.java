package com.bayviewglen.zork.items.weapons;
import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.items.Weapon;

public class Lucille extends Weapon{
	
	private int poisDmg = 0;
	private final int poisDmgInc = 10;
	private int poisLength = 4;
	private int[] poisStacks = new int[poisLength];

	public Lucille() {
		super("Lucille", 20, "Barbed wire baseball bat. Highly effective against asians and zombies.");
		// TODO Auto-generated constructor stub
	}
	
	public void ability(Monster monster, Player player){		
		int chance = (int) (Math.random() *7);
		
		if (chance <= 5) {
			monster.setHealth(monster.getHealth() - getDamage());
		
			poisDmg += poisDmgInc;
			for (int i = 0; i < poisStacks.length; i++) {
				if (poisStacks[i] == 0) {
					poisStacks[i] = poisLength;
				}
			}
			
			int stun = (int) (Math.random() * 2);
			
			if (stun == 0) {
				monster.stun(true);
			}
		}
		else {
			System.out.println("You have missed the hit!");
		}
		
		for (int i = 0; i < poisStacks.length; i++) {
			if (poisStacks[i] > 0) {
				poisStacks[i] -= 1;
			}
			
			if (poisStacks[i] == 0) {
				poisDmg -= poisDmgInc; 
			}
		}
		
		if (poisDmg > 0) {
			System.out.println("Plague Rat has been poisoned (-"+poisDmg+")");
			monster.setHealth(monster.getHealth() - poisDmg);
		}
	}
}
