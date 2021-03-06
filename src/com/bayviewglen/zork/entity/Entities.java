package com.bayviewglen.zork.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * used to store multiple entities and includes various methods 
 * that can determine certain things about the list of entities
 * like if it contains any monsters
 */

public class Entities {
	
	
	/*
	 * stores a map with a common name for the entity associate with the
	 * entity object
	 */
	private HashMap<String, Entity> entities = new HashMap<String, Entity>();
		
	/*
	 * Returns true if the map contains any monsters 
	 */
	public boolean hasMonsters() {
		for (Entity e : entities.values()) {
			if (e instanceof Monster) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * returns true if the map contains any NPC's
	 */
	public boolean hasNPC() {
		for (Entity e : entities.values()) {
			if (e instanceof NPC) {
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
	 * Remove monsters
	 */
	public void removeMonsters() {
		entities.entrySet().removeIf(entry -> entry.getValue() instanceof Monster);
	}
	
	/*
	 * Returns list of monsters
	 */
	public ArrayList<Entity> getMonsters() {
		ArrayList<Entity> monsters = new ArrayList<Entity>();
		for (Entity e : entities.values()) {
			if (e instanceof Monster) {
				monsters.add((Monster)e);
			}
		}
		return monsters;
	}
	
	/*
	 * Returns list of NPCs 
	 */
	public ArrayList<NPC> getNPCs() {
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		for (Entity e : entities.values()) {
			if (e instanceof NPC) {
				npcs.add((NPC)e);
			}
		}
		return npcs;
	}
	
	/*
	 * Returns a string list of NPCs
	 */
	public String getNPCList() {
		ArrayList<NPC> npcs = getNPCs();
		if (npcs.size() == 0) {
			return "nobody is here";
		}
		String ret = "";
		for (NPC npc : npcs) {
			ret += "\n- " + npc;
		}
		return ret;
	}

}
