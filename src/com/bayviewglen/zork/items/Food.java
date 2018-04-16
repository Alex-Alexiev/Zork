<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
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
=======
package zork.items;
=======
package com.bayviewglen.zork.items;

import java.util.HashMap;
>>>>>>> 9512bf8 Update Merge

public class Food extends Item {
	
	/*
	 * I removed the poison and generative properties 
	 * because we can just put negative for poison 
	 * and 0 or some other number for generative
	 */
	private int health;
	
	public Food(String id, int health) {
		super(id, 1, true);
		this.health = health;
	}
<<<<<<< Upstream, based on origin/master
	
	public int getHealthPoints() {
		return healthPoints;
>>>>>>> a2f39d0 This is the basic setup of the weapons class and Evan resynchronized
=======
		
	public int getHealthPoints(String item) {
		return health;
>>>>>>> 9512bf8 Update Merge
	}
	
}
