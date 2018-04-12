package com.bayviewglen.zork;
import com.bayviewglen.zork.items.Item;

/*
 * Inventory class for the player that keeps track of the player's items
 * 
 * The inventory is managed with two partially filled arrays, one which keeps track of the items 
 * and another which keeps track of the amounts of each item
 * 
 * Matthew Nam (April 12)
 */


public class Inventory {
	
	private Item[] items;
	private int[] amounts;
	private int size;
	
	public Inventory(int maxSize) {
		items = new Item[maxSize];
		amounts = new int[maxSize];
		size = 0;
	}
	
	/*
	 * Default inventory size is 10
	 */
	public Inventory() {
		items = new Item[10];
		amounts = new int[10];
		size = 0;
	}
	
	/*
	 * Adds an item to the inventory
	 * If the item is already in the inventory, it adds the given amount to the current amount
	 */
	public void addToInventory(Item item, int amount) {
		int i = checkItem(item.getId());
		if (i < 0) {
			if (size < items.length) {
				items[size] = item;
				size++;
				return;
			}
		} else {
			if (items[i].isConsumable()) {
				amounts[i] += amount;
				return;
			} else {
				System.out.println("You already have " + item.getId());
				return;
			}
		}
	}
	
	/*
	 * Checks for an item and returns its location
	 * If the item is not in the inventory, returns -1
	 */
	public int checkItem(String id) {
		for (int i = 0; i < size; i++) {
			if (items[i].compareId(id)) {
				return i;
			} 
		}
		return -1;
	}
	
	/*
	 * Returns the amount of item in the inventory
	 * If the item is not in the inventory, returns -1
	 */
	public int getItemAmount(Item item) {
		int i = checkItem(item.getId());
		if (!(i < 0)) {
			return amounts[i];
		}
		return -1;
	}
	
	/*
	 * Checks whether an item with a given id is in the inventory 
	 * If the item is in the inventory, returns the item
	 */
	public Item getItem(String id) {
		int i = checkItem(id);
		if (!(i < 0)) {
			return items[i];
		}
		return null;
	}
	
	/*
	 * Reduces the amount of an item
	 * If the amount is less than zero, the item is removed form the array
	 */
	public void usedItem(Item item, int amount) {
		for (int i = 0; i < size; i++) {
			if (items[i].compareId(item)) {
				amounts[i] -= amount;
				if (amounts[i] < 0)
					removeItem(item);
			} 
		}
	}
	
	/*
	 * Removes an item from the array
	 */
	public void removeItem(Item item) {
		for (int i = 0; i < size; i++) {
			if (items[i].compareId(item)) {
				for (int j = i; j < size - 1; j++) {
					items[i] = items[i+1];
					amounts[i] = amounts[i+1];
				}
				size--;
			} 
		}
	}
}
