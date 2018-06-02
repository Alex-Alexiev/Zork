package com.bayviewglen.zork.items;

import java.util.HashMap;

/*
 * Extends item class
 * A food item has a certain amount of 
 * health points that are gained when eaten
 */

public class Food extends Item {
	
	private int health;
	
	public Food(String name, int health) {
		super(name, 1, true);
		this.health = health;
	}
		
	public int getHealthPoints() {
		return health;
	}
	
}
