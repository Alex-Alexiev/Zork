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
	
	public void addToInventory(Item item) {
		//
		index++;
	}
}
