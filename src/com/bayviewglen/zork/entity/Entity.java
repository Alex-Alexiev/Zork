package com.bayviewglen.zork.entity;

import java.util.ArrayList;

import com.bayviewglen.zork.Poison;

public abstract class Entity {

	private String id;
	private String name;
	protected int health;
	protected boolean stunned;
	private ArrayList<Poison> poisons = new ArrayList<Poison>();

	public Entity(String name) {
		this.name = name;
		this.id = name.replaceAll("\\s", "").toLowerCase();
	}

	/*
	 * Damage methods
	 */
	public abstract void attack(Entity e);
	public void damage(int hitPoints) {
		health -= hitPoints;
	}

	/*
	 * Poison 
	 */
	public void addPoison(Poison poison) {
		this.poisons.add(poison);
	}
	public void poison() {
		int index = 0;
		while (index < poisons.size()) {
			Poison poison = poisons.get(index);
			damage(poison.damage());
			if (poison.isApplied()) {
				index++;
			} else {
				poisons.remove(index);
			}
		}
	}
	
	/*
	 * Getters
	 */
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getHealth() {
		return health;
	}
	public int getPoisonDamage() {
		int poisonDamage = 0;
		for (Poison poison : poisons) {
			poisonDamage += poison.getDamage();
		}
		return poisonDamage;
	}
	public boolean isPoisoned() {
		if (poisons.size() > 0) 
			return true;
		return false;
	}
	public boolean isStunned() {
		return stunned;
	}
	
	/*
	 * Setters
	 */
	public void stun(boolean s) {
		stunned = s;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
