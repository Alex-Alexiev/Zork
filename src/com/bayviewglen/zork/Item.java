package com.bayviewglen.zork;

public class Item {
	
	private String id;
	private boolean consummable;
	
	public Item(String id, boolean consummable) {
		this.id = id;
		this.consummable = consummable;
	}
	
	public Item() {}
	
	public String getId() {
		return this.id;
	}
	
	public boolean compareId(Item item) {
		if (this.id.equals(item.getId())) {
			return true;
		}
		return false;
	}
	
	public boolean isConsummable() {
		return this.consummable;
	}
	
}
