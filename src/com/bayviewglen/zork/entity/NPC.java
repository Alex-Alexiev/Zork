package com.bayviewglen.zork.entity;

import java.util.ArrayList;

public class NPC extends Entity {
	
	private ArrayList<String> responses;
	private int index = 0;
	
	public NPC(String name, ArrayList<String> responses) {
		super(name);
		this.responses = responses;
	}
	
	/*
	 * Returns a random reponse from the NPC's 
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
