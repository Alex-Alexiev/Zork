package com.bayviewglen.zork.items;

/* 
 * Item class that contains the following:
 *     - id that describes what it is (apple, sword, shield)
 *     - consumable that describes whether the item is consumable
 */

public class Item {
	
	private String id;
	private int amount = 0;
	private boolean consumable;
	
	public Item(String id, int amount, boolean consumable) {
		this.id = id;
		this.amount = amount;
		this.consumable = consumable;
	}
	
	public Item() {}
	
	/*
	 * Returns the item's id
	 */
	public String getId() {
		return this.id;
	}
	
	/*
	 * Compares this items id with another's
	 */
	public boolean compareId(Item item) {
		if (this.id.equals(item.getId())) {
			return true;
		}
		return false;
	}
	
	/*
	 * Compares this items id with another id
	 */
	public boolean compareId(String id) {
		if (this.id.equals(id)) {
			return true;
		}
		return false;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
		if (this.amount < 0) this.amount = 0;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String toString() {
		String ret = ""+amount+" ";
		if (amount > 1) ret += id+"'s";
		else ret += id;
		return ret;
	}
	
	
	
	/*
	 * Returns whether the item is consummable
	 */
	public boolean isConsumable() {
		return this.consumable;
	}
	
}
