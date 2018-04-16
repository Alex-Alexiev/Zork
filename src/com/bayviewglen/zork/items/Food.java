package com.bayviewglen.zork.items;

import java.util.HashMap;

public class Food extends Item {
	
	/*
	 * I removed the poison and generative properties 
	 * because we can just put negative for poison 
	 * and 0 or some other number for generative.
	 */
	private int health;
	
	public Food(String id, int health) {
		super(id, 1, true);
		this.health = health;
	}
		
	public int getHealthPoints(String item) {
		return health;
	}
	
}
