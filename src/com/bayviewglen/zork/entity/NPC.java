package com.bayviewglen.zork.entity;

import java.util.ArrayList;

public class NPC extends Entity {
	
	private ArrayList<String> responses;
	
	public NPC(String name, ArrayList<String> responses) {
		super(name);
		this.responses = responses;
	}
	
	public String getResponse() {
		int random = (int) (Math.random() * responses.size());
		return responses.get(random);
	}
}
