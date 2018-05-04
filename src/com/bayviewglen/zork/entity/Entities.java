package com.bayviewglen.zork.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entities {
	
	private HashMap<String, Entity> entities = new HashMap<String, Entity>();
	
	public boolean hasMonsters() {
		for (Entity e : entities.values()) {
			if (e instanceof Monster) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String ret = "Entities: ";
		for (Entity e : entities.values()) {
			ret+=e+", ";
		}
		return ret;
	}
	
	public void addEntity(Entity entity) {
		entities.put(entity.getId(), entity);
	}
	
	public Entity getWithId(String id) {
		return entities.get(id);
	}
	
	/*
	 * This is just a temporary method for testing the attacking
	 */
	public List<Monster> getMonsters(int i) {
		List<Monster> monsters = new ArrayList<Monster>();
		for (Entity e : entities.values()) {
			if (e instanceof Monster) {
				monsters.add((Monster)e);
			}
		}
		return monsters;
	}

}
