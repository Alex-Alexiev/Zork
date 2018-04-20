package com.bayviewglen.zork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import com.bayviewglen.zork.items.Food;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    March 2000
 * 
 *  This class is the main class of the "Zork" application. Zork is a very
 *  simple, text based adventure game.  Users can walk around some scenery.
 *  That's all. It should really be extended to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  routine.
 * 
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates the
 *  commands that the parser returns.
 */

class Game 
{
    private Player player;
    
    // This is a MASTER object that contains all of the rooms and is easily accessible.
    // The key will be the name of the room -> no spaces (Use all caps and underscore -> Great Room would have a key of GREAT_ROOM
    // In a hashmap keys are case sensitive.
    // masterRoomMap.get("GREAT_ROOM") will return the Room Object that is the Great Room (assuming you have one).
    private HashMap<String, Room> masterRoomMap;
    
    private void initRooms(String fileName) throws Exception{
    	masterRoomMap = new HashMap<String, Room>();
    	Scanner roomScanner;
		try {
			HashMap<String, HashMap<String, String>> exits = new HashMap<String, HashMap<String, String>>();    
			roomScanner = new Scanner(new File(fileName));
			while(roomScanner.hasNext()){
				String next = roomScanner.nextLine();
				if (!next.substring(0,2).equals("/s")) {
					Room room = new Room();
					
					// Read the Name
					String roomName = next.substring(10).trim();
					room.setRoomName(roomName.substring(0, roomName.length() - 2));
					
					// Read State of the room
					int roomState = Integer.parseInt(roomName.substring(roomName.length() - 1));
									
					// Gets the key of the room with state 1
					String roomOneKey = "";
					if (roomState != 1)
						roomOneKey = roomName.substring(0, roomName.length() - 2).toUpperCase().replaceAll(" ",  "_") + "_" + 1;
									
					// Read the Description  
					String roomDescription = roomScanner.nextLine();
					String des = roomDescription.split(":")[1].replaceAll("<br>", "\n").trim();
					if (roomState != 1 && des.trim().equals("")) {
						des = masterRoomMap.get(roomOneKey).getDescription();
					}
					room.setDescription(des);
	
					// Read the room inventory
					String roomInventory = roomScanner.nextLine();
					roomInventory = roomInventory.split(":")[1];
					String[] roomItems = roomInventory.split(",");
					if (roomState != 1 && roomInventory.trim().equals("")) {
						room.inventory = masterRoomMap.get(roomOneKey).inventory;
					} else {
						for (String item : roomItems) {
							item = item.substring(1);
							String type = item.split(" ")[0];
							if (type.equals("Food")) {
								Food food = new Food(item.split(" ")[1],Integer.parseInt(item.split(" ")[2]));
								room.inventory.addToInventory(food, 1);
							}
						}
					}
					
					roomScanner.nextLine();
					
					// Read the Exits
					String roomExits = roomScanner.nextLine();
					roomExits = roomExits.split(":")[1];
					
					// An array of strings in the format E-RoomName
					String[] rooms = roomExits.split(",");
					if (roomState != 1 && roomExits.trim().equals("")) {
						room.setExits(masterRoomMap.get(roomOneKey).getExits());
					} else {
						HashMap<String, String> temp = new HashMap<String, String>(); 
						for (String s : rooms){
							temp.put(s.split("-")[0].trim(), s.split("-")[1]);
						}
						exits.put(roomName.toUpperCase().replaceAll(" ",  "_"), temp);
					}
					
					// This puts the room we created (Without the exits in the masterMap)
					masterRoomMap.put(roomName.toUpperCase().replaceAll(" ",  "_"), room);
					
					// Now we better set the exits.
					if (roomScanner.hasNext()) roomScanner.nextLine();
				}
				else if (roomScanner.hasNext()) {
					roomScanner.nextLine();
				}
			}
			
			for (String key : masterRoomMap.keySet()){
				Room roomTemp = masterRoomMap.get(key);
				HashMap<String, String> tempExits = exits.get(key);
				for (String s : tempExits.keySet()){
					// s = direction
					// value is the room.
					String roomName2 = tempExits.get(s.trim());
					Room exitRoom = masterRoomMap.get(roomName2.toUpperCase().replaceAll(" ", "_").trim());
					roomTemp.setExit(s.trim().charAt(0), exitRoom);
				}
								
			}
    		roomScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }    

    /**
     * Create the game and initialize its internal map.
     */
    public Game() {
        try {
			initRooms("data/Rooms.dat");
			player = new Player(masterRoomMap.get("CABIN_1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished)
        {
        	finished = player.act();
        	System.out.println();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Zork!");
        System.out.println("Zork is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.printLocation();
        System.out.println();
    }
  
}