package com.bayviewglen.zork.items;


public class Weapons extends Item{
	
	private int damagePoints;
	private int fireDamage;
	
	public Weapons(String id, int damagePoints) {
		super(id, false);
		this.damagePoints = damagePoints;
		
	}
	
	
	public int getDamagePoints() {
		return damagePoints;
	}

}
