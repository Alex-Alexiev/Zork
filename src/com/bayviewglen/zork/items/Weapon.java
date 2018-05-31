package com.bayviewglen.zork.items;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.weapons.BareHands;

public class Weapon extends Item{
	
	/*
	 * Data fields
	 */
	private int damage;
	private int chance;
	private double CRIT_PERCENT = 0.5;
	private int CRIT_CHANCE = 20;
	private int scaler;
	private int durability;
	private int maxDur;
	
	/*
	 * Constructor
	 */
	public Weapon(String name, String description, int damage, int chance, int dur) {
		super(name, 1, false);
		this.damage = damage;
		this.chance = chance;
		this.scaler = 1;
		this.durability = dur;
		this.maxDur = dur;
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
		if (this instanceof BareHands) {
			return super.getName() + "\n" + getDescription() + "\n Damage: " + this.damage + "\n Durability: " + this.durability + "/infinity";
		}
		return super.getName() + "\n" + getDescription() + "\n Damage: " + this.damage + "\n Durability: " + this.durability + "/" + this.maxDur;
	}
	
	/*
	 * Attack method
	 */
	private void ability(Entity e, Player p){
		if (didHit()) {
			int dam = (int)(getDamage() * p.getDamageScaler() + criticalHit());
			e.setHealth(e.getHealth() - dam);
			System.out.println("You attack " + e.getName() + " with " + getName() + " (-" + dam + ")");
		}
	}
	
	/*
	 * Use
	 */
	public void use(Entity e, Player p) {
		ability(e, p);
		if (durability > 0)
			durability--;
		if (durability == 0) {
			System.out.println(this.getName() + " has worn out");
			p.removeWeapon();
		}
	}

}
