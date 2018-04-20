package com.bayviewglen.zork.items;

import com.bayviewglen.zork.entity.Monster;

abstract public class Weapon extends Item{
	
	protected int damage;
	
	public Weapon(String id, int damage, String description) {
		super(id, 1, false);
		this.damage = damage;
		this.description = description;
	}
	
	abstract public void ability(Monster monster);

}
