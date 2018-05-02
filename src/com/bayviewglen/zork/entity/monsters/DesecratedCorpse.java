package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

public class DesecratedCorpse  extends Monster {

	public DesecratedCorpse() {
		super("DesecratedCorpse", "A rotten corpse, with its flesh <br>flayed to emulate twisted cult symbols", 55,550);
	}

	public void ability(Player player) {
			int num = (int) (Math.random() *6);
			
		
			if (num < 4) {
				player.normalDamage(damage); 
				
				
			
		}
	}
}