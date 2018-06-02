package com.bayviewglen.zork;

/*
 * A poison object can be added to any entity 
 * When an entity has a poison, it gets damaged by a certain amount determined by 
 * its poison each turn. 
 */

public class Poison {
	private int damage;
	private int damageInc;
	private int length;
	/*
	 * if the poison is finalAction then all the damage is incurred on the final turn
	 * Otherwise the damage is spread evenly across each turn
	 */
	private boolean finalAction;
	private String id;

	public Poison(int inc, int length) {
		this.damage = 0;
		this.damageInc = inc;
		this.length = length;
		this.damage += this.damageInc;
		finalAction = false;
	}

	public void setFinalAction(boolean b) {
		finalAction = b;
	}

	public void setId(String s) {
		id = s;
	}

	public String getId() {
		return id;
	}

	public boolean isFinalAction() {
		return finalAction;
	}
	
	/*
	 * Determine the damage that the poison will give 
	 */
	public int damage() {
		this.length--;
		if (isApplied()) {
			return this.damage;
		}
		return 0;
	}

	public int getDamage() {
		return this.damage;
	}
	
	/*
	 * Determines whether or not the poison should be applied
	 * If it is final action this only returns true on the last turn
	 * Otherwise it will do the opposite and only return true if it is not he final turn
	 */

	public boolean isApplied() {
		if (!finalAction) {
			if (this.length == 0)
				return false;
			return true;
		}
		if (this.length == 0) {
			return true;
		}
		return false;
	}
	
	public int getLength() {
		return length;
	}
}
