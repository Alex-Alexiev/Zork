package com.bayviewglen.zork;

public class Player {
	
	private Room currentRoom;
	
	public Player(Room startingRoom) {
		currentRoom = startingRoom;
	}
	
	public void printLocation() {
		System.out.println(currentRoom.longDescription());
	}
	
	public void move(String direction) {
		Room nextRoom = currentRoom.nextRoom(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else 
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.longDescription());
        }
	}

}
