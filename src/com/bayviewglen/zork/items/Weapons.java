package com.bayviewglen.zork.items;

import com.bayviewglen.zork.monsters.Monster;

abstract class Weapons extends Item{
	
	protected int damage;
	
	public Weapons(String id, int damage, String description) {
		super(id, 1, false);
		this.damage = damage;
		this.description = description;
	}
	
	abstract public void ability(Monster monster);

}
