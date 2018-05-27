package com.bayviewglen.zork.entity;

abstract public class Monster extends Entity {

	private int damage;
	private String description;
	private int hitOdds;

	public Monster(String id, String description, int damage, int hitOdds, int health) {
		super(id);
		this.damage = damage;
		this.health = health;
		this.description = description;
		this.stunned = false;
		this.hitOdds = hitOdds;
	}

	public void attack(Entity e) {
		if (Math.random() < (hitOdds/100.0)) {
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			((Player) e).hit(getDamage());
		} else {
			System.out.println(getId() + " has missed the hit!");
		}
	}

	public int getDamage() {
		return this.damage;
	}
	
	public int getHitOdds() {
		return this.hitOdds;
	}

	public String toString() {
		return getName() + ": " + description;
	}
}
