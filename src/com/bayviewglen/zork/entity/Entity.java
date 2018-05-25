package com.bayviewglen.zork.entity;

public abstract class Entity {
	
	private String id;
	private String name;
	
	public Entity(String name) {
		this.name = name;
		this.id = name.replaceAll("\\s","").toLowerCase();
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
