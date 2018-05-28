package com.bayviewglen.zork.items;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;

public class Weapon extends Item{
	
	/*
	 * Data fields
	 */
	private int damage;
	private int chance;
	private double CRIT_PERCENT = 0.5;
	private int CRIT_CHANCE = 20;
	
	/*
	 * Constructor
	 */
	public Weapon(String name, String description, int damage, int chance) {
		super(name, 1, false);
		this.damage = damage;
		this.chance = chance;
		setDescription(description);
	}
	
	/*
	 * Getters
	 */
	public int getDamage() {
		return damage;
	}
	public int getChance() {
		return chance;
	}
	
	/*
	 * Decides whether the attack hit
	 */
	public boolean didHit() {
		int num = (int) (Math.random() * 100);
		if (num < chance)
			return true;
		else 
			System.out.println("You have missed the hit!");
		return false;
	}
	
	/*
	 * Decides whether critical hit
	 */
	public int criticalHit() {
		int num = (int) (Math.random() * 100);
		if (num < CRIT_CHANCE) {
			System.out.println("Critical Hit!");
			return (int) (damage + damage * CRIT_PERCENT);
		}
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bayviewglen.zork.items.Item#toString()
	 */
	public String toString() {
		return super.getName();
	}
	
	public String longDescription() {
		return super.getName() + "\n" + getDescription() + "\n Damage: " + this.damage;
	}
	
	/*
	 * Attack method
	 */
	public void ability(Entity e, Player p){
		if (didHit()) {
			e.setHealth(e.getHealth() - getDamage() - criticalHit());
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + getDamage() + ")");
		}
	}

}
