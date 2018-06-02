package com.bayviewglen.zork.items;

import com.bayviewglen.zork.entity.Player;

/*
 * Basic item that has more children, but essentially 
 * provides extra protection to any entity that has an armor.
 * 
 * The individual armor's may have unique properties
 */

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
	
	public void setShieldPoints(int i) {
		shieldPoints = i;
	}
	
	public void damage(int damage, Player player) {
		shieldPoints -= ability(damage, player);
	}
	
	abstract public int ability(int damage, Player player);
	
	public String toString() {
		return super.getName();
	}
	
	public String longDescription() {
		return super.getName() + "\n" + getDescription() + "\n  Shield Points: " + this.shieldPoints;
	}
}
