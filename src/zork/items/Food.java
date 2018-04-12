package com.bayviewglen.zork;

public class Food extends Item {
	
	private int healthPoints;
	private boolean poison;
	private boolean generative;
	
	public Food(String id, int healthPoints) {
		super(id, true);
		this.healthPoints = healthPoints;
		
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	
}
