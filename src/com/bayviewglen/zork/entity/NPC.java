package com.bayviewglen.zork.entity;

import java.util.ArrayList;

/*
 * An NPC (non playable character) is used to give 
 * helpful (or not so helpful) tips to the player
 * 
 * It has responses from the NPC data file and 
 * randomly picks which one to deliver 
 */

public class NPC extends Entity {
	
	private ArrayList<String> responses;
	private int index = 0;
	
	public NPC(String name, ArrayList<String> responses) {
		super(name);
		this.responses = responses;
	}
	
	/*
	 * Returns a random response from the NPC's 
	 * list of responses
	 */
	public String getResponse() {
		String ret = responses.get(index);
		index++;
		if (index >= responses.size())
			index = 0;
		return ret;
	}
	
	public String toString() {
		return this.getName();
	}

	public void attack(Entity e) {
		e.damage(0);
	}
}
