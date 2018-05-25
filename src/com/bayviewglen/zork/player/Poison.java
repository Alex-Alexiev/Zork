package com.bayviewglen.zork.player;

public class Poison {
	private int damage;
	private int damageInc;
	private int[] stacks;
	
	public Poison(int inc, int[] stacks) {
		this.damage = 0;
		this.damageInc = inc;
		this.stacks = stacks;
	}
	
	public void poisoned() {
		damage += damageInc;
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i] == 0) {
				stacks[i] = stacks.length;
				return;
			}
		}
	}
	
	public int tick() {
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i] > 0) {
				stacks[i] -= 1;
			}
			if (stacks[i] == 0) {
				damage -= damageInc;
			}
		}
		return damage;
	}
}
