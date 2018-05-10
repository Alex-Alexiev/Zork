package com.bayviewglen.zork.entity;

public abstract class Entity {
	
	private String id;
	
	public Entity(String id) {
		this.id = id.toLowerCase();
	}
	
	public String getId() {
		return id;
	}
}
