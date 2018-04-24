package com.bayviewglen.zork.entity;

import java.util.HashMap;

public class Entities {
	
	private HashMap<String, Entity> entities = new HashMap<String, Entity>();
	
	public void addEntity(Entity entity) {
		entities.put(entity.getId(), entity);
	}
	
	public Entity getWithId(String id) {
		return entities.get(id);
	}

}
