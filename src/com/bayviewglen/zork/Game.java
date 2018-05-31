package com.bayviewglen.zork;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.bayviewglen.zork.entity.Entities;
import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.NPC;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.items.Armor;
import com.bayviewglen.zork.items.Food;
import com.bayviewglen.zork.items.Item;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author: Michael Kolling Version: 1.1 Date: March 2000
 * 
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game. Users can walk around some scenery. That's
 * all. It should really be extended to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * 
 * This main class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates the commands that
 * the parser returns.
 */

class Game {
	private Player player;

	// This is a MASTER object that contains all of the rooms and is easily
	// accessible.
	// The key will be the name of the room -> no spaces (Use all caps and
	// underscore -> Great Room would have a key of GREAT_ROOM
	// In a hashmap keys are case sensitive.
	// masterRoomMap.get("GREAT_ROOM") will return the Room Object that is the Great
	// Room (assuming you have one).
	private HashMap<String, Room> masterRoomMap;
	private HashMap<String, ArrayList<String>> npcData;

	private void initRooms(String fileName) throws Exception {
		masterRoomMap = new HashMap<String, Room>();
		Scanner roomScanner;
		try {
			HashMap<String, HashMap<String, String>> exits = new HashMap<String, HashMap<String, String>>();
			roomScanner = new Scanner(new File(fileName));
			while (roomScanner.hasNext()) {
				String next = roomScanner.nextLine();
				if (!next.substring(0, 2).equals("/s")) {
					
					Room room = new Room();

					// Read the Name
					String roomName = next.substring(10).trim();
					room.setRoomName(roomName.substring(0, roomName.length() - 2));

					// Read State of the room
					int roomState = Integer.parseInt(roomName.substring(roomName.length() - 1));
					// Gets the key of the room with state 1
					String roomOneKey = "";
					if (roomState != 1)
						roomOneKey = roomName.substring(0, roomName.length() - 2).toUpperCase().replaceAll(" ", "_")
								+ "_" + 1;

					// Read the Description
					String roomDescription = roomScanner.nextLine();
					String des = roomDescription.split(":")[1].replaceAll("<br>", "\n").trim();
					if (roomState != 1 && des.trim().equals("")) {
						des = masterRoomMap.get(roomOneKey).getDescription();
					}
					room.setDescription(des);

					// Read the room inventory
					String roomInventory = roomScanner.nextLine();
					if (roomState != 1) {
						room.inventory = masterRoomMap.get(roomOneKey).inventory;
					}
					if (roomInventory.split(":").length > 1) {
						roomInventory = roomInventory.split(":")[1];
						String[] roomItems = roomInventory.split(",");
						for (String item : roomItems) {
							item = item.substring(1);
							String[] itemInfo = item.split(" ");
							String type = itemInfo[0];
							String name = "";
							for (int i = 1; i < itemInfo.length - 1; i++)
								name += itemInfo[i];
							if (type.equals("Food")) {
								Food food = new Food(name, Integer.parseInt(itemInfo[itemInfo.length - 1]));
								room.inventory.addToInventory(food, 1);
							}
							if (type.equals("Armor")) {
								Class<?> clazz = Class
										.forName("com.bayviewglen.zork.items.armor." + itemInfo[1].trim());
								Constructor<?> ctor = clazz.getConstructor();
								Item object = (Item) ctor.newInstance();
								room.inventory.addToInventory(object, 1);
							}
							if (type.equals("Weapon")) {
								Class<?> clazz = Class
										.forName("com.bayviewglen.zork.items.weapons." + itemInfo[1].trim());
								Constructor<?> ctor = clazz.getConstructor();
								Item object = (Item) ctor.newInstance();
								room.inventory.addToInventory(object, 1);
							}
						}
					}

					// Read the room entities (Monsters and NPC's)
					String roomEntities = roomScanner.nextLine();
					if (roomState != 1) {
						room.entities = masterRoomMap.get(roomOneKey).entities;
					}
					if (roomEntities.split(":").length > 1) { // first room
						roomEntities = roomEntities.split(":")[1].trim();
						String[] roomEntitiesArray = roomEntities.split(",");
						for (String entity : roomEntitiesArray) {
							entity = entity.trim();
							String type = entity.split(" ")[0];
							if (type.equals("Monster")) {
								Class<?> clazz = Class
										.forName("com.bayviewglen.zork.entity.monsters." + entity.split(" ")[1].trim());
								Constructor<?> ctor = clazz.getConstructor();
								Entity object = (Entity) ctor.newInstance();
								room.entities.addEntity(object);
							} 
							else if (type.equals("NPC")) {
								String name = entity.substring(4);
								ArrayList<String> responses = npcData.get(name);
								
								NPC npc = new NPC(name, responses);
								room.entities.addEntity(npc);
							}
						}
					}
					// Read the Exits
					String roomExits = roomScanner.nextLine();
					roomExits = roomExits.split(":")[1];
					// An array of strings in the format E-RoomName
					String[] rooms = roomExits.split(",");
					if (roomState != 1 && roomExits.trim().equals("")) {
						room.setExits(masterRoomMap.get(roomOneKey).getExits());
					} else {
						HashMap<String, String> temp = new HashMap<String, String>();
						for (String s : rooms) {
							temp.put(s.split("-")[0].trim(), s.split("-")[1]);
						}
						exits.put(roomName.toUpperCase().replaceAll(" ", "_"), temp);
					}
					// This puts the room we created (Without the exits in the masterMap)
					masterRoomMap.put(roomName.toUpperCase().replaceAll(" ", "_"), room);
					// Now we better set the exits.
					if (roomScanner.hasNext())
						roomScanner.nextLine();
				} else if (roomScanner.hasNext()) {
					roomScanner.nextLine();
				}
			}

			for (String key : masterRoomMap.keySet()) {
				Room roomTemp = masterRoomMap.get(key);
				HashMap<String, String> tempExits = exits.get(key);
				for (String s : tempExits.keySet()) {
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
	
	private void initNPCs(String fileName) {
		npcData = new HashMap<String, ArrayList<String>>();
		Scanner npcScanner;
		try {
			npcScanner = new Scanner(new File(fileName));
			while (npcScanner.hasNext()) {
				String[] firstLine = npcScanner.nextLine().split(" ");
				String name = firstLine[0];
				int numResponses = Integer.parseInt(firstLine[firstLine.length - 1]);
				
				npcData.put(name, new ArrayList<String>());
				
				for (int i = 0; i < numResponses; i++) {
					String response = npcScanner.nextLine();
					npcData.get(name).add(response);
				}
				if (npcScanner.hasNext())
					npcScanner.nextLine();
			}
			npcScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		initialize();
	}
	
	private void initialize() {
		try {
			initNPCs("data/npc.dat");
			initRooms("data/Rooms.dat");
			player = new Player(masterRoomMap.get("CABIN_1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();
		Sound mainThemeMusic = new Sound("data\\mainmusic.wav");
		mainThemeMusic.loop();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = Parser.getCommand();
			finished = player.act(command);
			System.out.println();
		}
		System.out.println("Thank you for playing.  Good bye.");
		initialize();
		play();
	}

	/**
	 * Print out the opening message for the player.
	 */
	
	private void printWelcome() {
		System.out.println();
		System.out.println("Hello adventurer!");
		System.out.println("This is the beginning of a long and perilous journey.");
		System.out.println("Your village has been suffering from an intense famine.");
		System.out.println("All the crops are dying, rotten and diseased.");
		System.out.println("The village elders say this is a curse from an island far off the coast.");
		System.out.println("It is there that a most unholy force imposes itself on all within its proximity.");
		System.out.println("Tearing the minds of the living, and distorting the flesh of the dead.");
		System.out.println("This is the beginning of a long and perilous journey.");
		System.out.println("You must end this curse once and for all.");
		System.out.println("You must slay the Heart Of The Island");
		System.out.println("Of course, if you need help, type 'help' and the great spirit will guide you.");
		System.out.println();
		player.printLocation();
		System.out.println();
	}

}