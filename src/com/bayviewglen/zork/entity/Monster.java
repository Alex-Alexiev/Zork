package com.bayviewglen.zork.entity;

import com.bayviewglen.zork.player.Player;
import com.bayviewglen.zork.player.Poison;

abstract public class Monster extends Entity {
	
	private int damage;
	private int health;
	private boolean stunned;
	private String description;
	private Poison poison;
	
	public Monster(String id, String description, int damage, int health) {
		super(id);
		this.damage = damage;
		this.health = health;
		this.description = description;
		this.stunned = false;
	}
	
	abstract public int ability(Player player);
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public boolean isStunned() {
		return stunned;
	}
	
	public void stun(boolean s) {
		stunned = s;
	}
	
	public String toString() {
		return getName() + ": " + description;
	}
	
	/*
	 *Poison Method
	 */
	public void poisoned(Poison poison) {
		this.poison = poison;
		this.poison.poisoned();
	}
	
	/*
	 * Poison tick method
	 */
	public void poisonTick() {
		health -= poison.tick();
	}
}
