package com.bayviewglen.zork.entity;

public class NPC extends Entity {
	
	private String response;
	
	public NPC(String id, String description, String response) {
		super(id, description);
	}
	
	public void respond() {
		System.out.println(response);
	}
	
}
