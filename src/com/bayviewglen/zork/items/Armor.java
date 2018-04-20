package com.bayviewglen.zork.items;

abstract public class Armor extends Item {
	
	private int shieldPoints;
	
	public Armor(String id, int shieldPoints, String description) {
		super(id, 1, false);
		this.shieldPoints = shieldPoints;
		this.description = description;
	}
	
	public int getShieldPoints() {
		return shieldPoints;
	}
	
	abstract public void ability();
	
	public String toString() {
		return super.getId();
	}
}
