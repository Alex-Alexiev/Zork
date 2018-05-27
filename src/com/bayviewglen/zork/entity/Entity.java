package com.bayviewglen.zork.entity;

import java.util.ArrayList;

import com.bayviewglen.zork.Poison;

public abstract class Entity {

	private String id;
	public boolean hasArmor;
	private String name;
	protected int health;
	protected boolean stunned;
	public boolean canGivePoison;
	private ArrayList<Poison> poisons = new ArrayList<Poison>();

	public Entity(String name) {
		this.name = name;
		this.id = name.replaceAll("\\s", "").toLowerCase();
		hasArmor = false;
		canGivePoison = false;
	}

	public abstract void attack(Entity e);

	public void damage(int hitPoints) {
		health -= hitPoints;
	}

	public void addPoison(Poison poison) {
		this.poisons.add(poison);
	}

	public void poison() {
		for (Poison p : poisons) {
			damage(p.damage());
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public boolean isStunned() {
		return stunned;
	}
	
	public boolean canGivePoison() {
		return canGivePoison;
	}

	public void stun(boolean s) {
		stunned = s;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Poison getPoison() {
		return null;
	}
}
