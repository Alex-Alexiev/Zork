package com.bayviewglen.zork;

public class Poison {
	private int damage;
	private int damageInc;
	private int length;
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
