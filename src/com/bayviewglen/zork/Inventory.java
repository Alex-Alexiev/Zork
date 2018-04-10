package com.bayviewglen.zork;

public class Inventory {
	
	private Item[] items;
	private int[] amounts;
	private int index;
	
	public Inventory(int maxSize) {
		items = new Item[maxSize];
		amounts = new int[maxSize];
		index = 0;
	}
	
	public Inventory() {
		items = new Item[10];
		amounts = new int[10];
		index = 0;
	}
	
	public void addToInventory(Item item, int amount) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareId(item)) {
				if (items[i].isConsummable()) {
					amounts[i] += amount;
					return;
				} else {
					System.out.println("You already have " + item.getId());
					return;
				}
			} else {
				if (index < items.length) {
					items[i] = item;
					index++;
					return;
				}
			}
		}
	}
	
	public boolean checkItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareId(item)) {
				return true;
			} 
		}
		return false;
	}
	
	public int checkItemAmount(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareId(item)) {
				return amounts[i];
			} 
		}
		return -1;
	}
	
	public Item getItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareId(item)) {
				return item;
			} 
		}
		return null;
	}
	
	public void usedItem(Item item, int amount) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareId(item)) {
				amounts[i] -= amount;
				if (amounts[i] < 0)
					removeItem(item);
			} 
		}
	}
	
	public void removeItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareId(item)) {
				// remove the item
			} 
		}
	}
}
