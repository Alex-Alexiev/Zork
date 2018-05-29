package com.bayviewglen.zork;
/*
 * Author:  Michael Kolling.
 * Version: 1.0
 * Date:    July 1999
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 * 
 * This class is part of the "Zork" game.
 */

import java.util.HashMap;

public class CommandWords {
	// a constant array that returns the main valid command words
	private static final String showCommands[] = { "go/move", "help", "quit", "search", "room", "equip", "equipment",
			"welfare", "eat", "take", "drop", "inventory", "unequip", };

	private static HashMap<String, String[]> wordGroups = new HashMap<String, String[]>();
	private static boolean first = true;

	public static boolean is(String commandGroup, String command) {
		if (first) {
			first = false;
			wordGroups.put("move", new String[] { "go", "move", "walk", "run" });
			wordGroups.put("location", new String[] { "room", "place", "location", "spot" });
			wordGroups.put("search", new String[] { "search", "find", "look" });
			wordGroups.put("inventory", new String[] { "inventory", "items", "things", "supply" });
			wordGroups.put("eat", new String[] { "eat", "consume", "bite", "chew", "ingest", "snack", "munch", "gorge",
					"dine", "lunch" });
			wordGroups.put("pickup", new String[] { "take", "retrieve", "aquire", "recieve", "snag", "grab" });
			wordGroups.put("equipment", new String[] { "weapons", "armor", "equipment", "shield", "apparatus",
					"devices", "attachments", "gadgets", "outfit", "tools" });
			wordGroups.put("unequip", new String[] { "unequip", "holster" });
		}
		for (String s : wordGroups.get(commandGroup)) {
			if (s.equals(command)) {
				return true;
			}
		}
		return false;
	}

	// a constant array that holds all valid command words
	private static final String validCommands[] = { "go", "move", "walk", "run", "help", "quit", "search", "find",
			"look", "room", "place", "location", "spot", "equip", "equipment", "welfare", "eat", "consume", "bite",
			"chew", "ingest", "snack", "munch", "gorge", "dine", "lunch", "ingest", "take", "retrieve", "aquire",
			"recieve", "snag", "grab", "drop", "inventory", "items", "things", "supply", "weapons", "armor",
			"equipment", "shield", "apparatus", "devices", "attachments", "gadgets", "outfit", "tools", "unequip",
			"holster", "talk" };

	/**
	 * Constructor - initialise the command words.
	 */
	public CommandWords() {
		// nothing to do at the moment...
	}

	/**
	 * Check whether a given String is a valid command word. Return true if it is,
	 * false if it isn't.
	 **/
	public static boolean isCommand(String aString) {
		for (int i = 0; i < validCommands.length; i++) {
			if (validCommands[i].split(" ")[0].equals(aString))
				return true;
		}
		// if we get here, the string was not found in the commands
		return false;
	}

	/*
	 * Print all valid commands to System.out.
	 */
	public void showAll() {
		for (int i = 0; i < showCommands.length; i++) {
			System.out.print(showCommands[i] + " | ");
			if (i % 8 == 0 && i != 0)
				System.out.println();
		}
		System.out.println();
	}
}
