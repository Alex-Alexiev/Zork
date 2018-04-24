package com.bayviewglen.zork.entity;

public class Entity {
	
	String id;
	String description;
	
	public Entity(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
}
