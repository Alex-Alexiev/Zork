package com.bayviewglen.zork.entity;

public abstract class Entity {
	
	String id;
	String description;
	
	public Entity(String id, String description) {
		this.id = id.toLowerCase();
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
}
