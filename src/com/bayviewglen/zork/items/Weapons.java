package com.bayviewglen.zork.items;

import com.bayviewglen.zork.Monster;

abstract class Weapons extends Item{
	
	private int damage;
	
	public Weapons(String id, int damage) {
		super(id, 1, false);
		this.damage = damage;
		
	}
	
	
	public int getDamage() {
		return damage;
	}
	
	abstract public void ability(Monster monster);

}
