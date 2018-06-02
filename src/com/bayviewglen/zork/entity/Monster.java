package com.bayviewglen.zork.entity;

/*
 * Extends entity. Has damage, description, and chances of hitting
 */

abstract public class Monster extends Entity {

	private int damage;
	private String description;
	private int hitOdds;
	private int prevHealth;

	public Monster(String id, String description, int damage, int hitOdds, int health) {
		super(id);
		this.damage = damage;
		this.health = health;
		this.prevHealth = health;
		this.description = description;
		this.stunned = false;
		this.hitOdds = hitOdds;
	}
	
	/*
	 * Default attack method.
	 * There is a hittOdds percent chance the monster hits the entity
	 * If it hits it does the default damage.
	 * This is overriden in many of the individual monster classes 
	 */
	public void attack(Entity e) {
		if (Math.random() < (hitOdds/100.0)) {
			System.out.println(this.getName() + " has attacked you (-" + this.getDamage() + ")");
			((Player) e).hit(getDamage());
		} else {
			System.out.println(getName() + " has missed the hit!");
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
	
	public int getPrevHealth() {
		return prevHealth;
	}
	
	public void setPrevHealth(int i) {
		prevHealth = i;
	}
}
