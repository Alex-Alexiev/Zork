package com.bayviewglen.zork.entity;

import java.util.ArrayList;

public class NPC extends Entity {
	
	private ArrayList<String> responses;
	
	public NPC(String id, ArrayList<String> responses) {
		super(id);
		this.responses = responses;
	}
	
	public String getResponse() {
		int random = (int) (Math.random() * responses.size());
		return responses.get(random);
	}
}
