package com.bayviewglen.zork.items;

import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.player.Player;

abstract public class Weapon extends Item{
	
	/*
	 * Data fields
	 */
	private int damage;
	private int chance;
	private int CRIT_PERCENT = 50;
	private int CRIT_CHANCE = 20;
	
	/*
	 * Constructor
	 */
	public Weapon(String id, String description, int damage, int chance) {
		super(id, 1, false);
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
			return damage * CRIT_PERCENT;
		}
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bayviewglen.zork.items.Item#toString()
	 */
	public String toString() {
		return super.getId() + "\n  Damage: " + damage;
	}
	
	/*
	 * Attack method
	 */
	abstract public void ability(Monster monster, Player player);

}
