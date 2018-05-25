package com.bayviewglen.zork.items;

abstract public class Armor extends Item {
	
	private int shieldPoints;
	
	public Armor(String name, int shieldPoints, String description) {
		super(name, 1, false);
		this.shieldPoints = shieldPoints;
		setDescription(description);
	}
	
	public int getShieldPoints() {
		return shieldPoints;
	}
	
	public void damage(int damage) {
		shieldPoints -= damage;
	}
	
	abstract public void ability();
	
	public String toString() {
		return super.getName();
	}
	
	public String longDescription() {
		return super.getName() + "\n" + getDescription() + "\n  Shield Points: " + this.shieldPoints;
	}
}
