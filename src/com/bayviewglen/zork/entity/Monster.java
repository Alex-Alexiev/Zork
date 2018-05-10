package com.bayviewglen.zork.entity;

import com.bayviewglen.zork.Player;

abstract public class Monster extends Entity {
	
	protected int damage;
	
	protected int health;
	
	public Monster(String id, String description, int damage, int health) {
		super(id, description);
		this.damage = damage;
		this.health = health;
	}
	
	abstract public int ability(Player player);
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String toString() {
		return id;
	}
	
	
}
