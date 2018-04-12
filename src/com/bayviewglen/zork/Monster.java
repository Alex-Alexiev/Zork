package com.bayviewglen.zork;

import com.bayviewglen.zork.Player;

abstract public class Monster {
	private String id;
	
	private String description;
	
	//private 
	
	private int damage;
	
	private int health;
	
	public Monster(String id, String description, int damage, int health) {
		this.id = id;
		this.description = description;
		this.damage = damage;
		this.health = health;
	}
	
	abstract public void ability(Player player);
	
}
