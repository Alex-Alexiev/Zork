package com.bayviewglen.zork;

public class Poison {
	private int damage;
	private int damageInc;
	private int length;
	
	public Poison(int inc, int length) {
		this.damage = 0;
		this.damageInc = inc;
		this.length = length;
		this.damage += this.damageInc;
	}
	
	public int damage() {
		this.length--;
		return this.damage;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public boolean isApplied() {
		if (this.length == 0)
			return false;
		return true;
	}
}
