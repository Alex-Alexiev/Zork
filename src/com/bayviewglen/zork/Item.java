package com.bayviewglen.zork;

public class Item {
	
	private String id;
	private boolean consummable;
	
	public Item(String id, boolean consummable) {
		this.id = id;
		this.consummable = consummable;
	}
	
	public Item() {}
	
}
