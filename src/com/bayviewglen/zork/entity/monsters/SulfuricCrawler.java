package com.bayviewglen.zork.entity.monsters;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class SulfuricCrawler extends Monster {
	
	public SulfuricCrawler() {
		super("Sulfuric Crawler", "Large spider made of twisted, mutated human remains. \n Pustules cover its body, filled with fluorescent green liquid.", 40, 70, 150);
	}
	
	public void deadMove(Player p) {
		p.removeArmor();
		System.out.println("Your armor whithers away");
	}
	

}
