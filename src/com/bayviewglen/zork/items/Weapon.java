package com.bayviewglen.zork.items;

import com.bayviewglen.zork.Player;
import com.bayviewglen.zork.entity.Monster;

abstract public class Weapon extends Item{
	
	private int damage;
	
	public Weapon(String id, int damage, String description) {
		super(id, 1, false);
		this.damage = damage;
		setDescription(description);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public String toString() {
		return super.getId() + "\n  Damage: " + damage;
	}
	
	abstract public void ability(Monster monster, Player player);

}
