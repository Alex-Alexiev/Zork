package com.bayviewglen.zork.items;

import com.bayviewglen.zork.monsters.Monster;

abstract class Weapons extends Item{
	
	protected int damage;
	
	public Weapons(String id, int damage) {
		super(id, 1, false);
		this.damage = damage;
		
	}
	
	
	public int getDamage() {
		return damage;
	}
	
	abstract public void ability(Monster monster);

}
