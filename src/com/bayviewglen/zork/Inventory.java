package com.bayviewglen.zork;
import java.util.ArrayList;
import java.util.HashMap;

import com.bayviewglen.zork.items.Item;

/*
 * Inventory class for the player that keeps track of and inventory of items
 * 
 * It uses a HashMap of Items, each referenced by its id
 * 
 * 
 * Matthew Nam (April 12), Alex Alexiev (April 13)
 */


public class Inventory {
	
	private HashMap<String, Item> items = new HashMap<String, Item>();

	/*
	 * Adds an item to the inventory
	 * If the item is already in the inventory, it adds the given amount to the current amount
	 */
	public void addToInventory(Item item, int amount) {
		if (items.containsKey(item.getId())) {
			items.get(item.getId()).addAmount(amount);
		}
		else {
			items.put(item.getId(), item);
		}
	}
	
	/*
	 * Checks for an item and returns whether or not its in the inventory
	 */
	public boolean checkItem(String id) {
		return items.containsKey(id);
	}
	
	public boolean checkItem(Item item) {
		return items.containsValue(item);
	}
	
	/*
	 * Returns the amount of item in the inventory
	 * If the item is not in the inventory, returns -1
	 */
	public int getItemAmount(String id) {
		if (checkItem(id)) {
			return items.get(id).getAmount();
		}
		return -1;
	}
	
	/*
	 * Checks whether an item with a given id is in the inventory 
	 * If the item is in the inventory, returns the item
	 */
	public Item getItem(String id) {
		if (items.containsKey(id)) {
			return items.get(id); 
		}
		return null;
	}
	
	public String toString() {
		String ret = "";
		for(Item item: items.values()) {
			ret += item+"\n";
		}
		if (ret.length()<1) {
			return "LOL you have nothing in your inventory XD";
		}
		return ret;
	}
	
	/*
	 * Reduces the amount of an item
	 * If the amount is less than zero, the item is removed form the array
	 */
	public void reduceAmount(String id, int amount) {
		items.get(id).addAmount(-amount);
		if (items.get(id).getAmount() < 1) {
			removeItem(id);
		}
	}
	
	/*
	 * Removes an item from the array
	 */
	public void removeItem(String id) {
		items.remove(id);
	}
}
