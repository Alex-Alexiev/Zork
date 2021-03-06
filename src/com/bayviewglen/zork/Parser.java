package com.bayviewglen.zork;
/*
 * Author:  Michael Kolling
 * Version: 1.0
 * Date:    July 1999
 * 
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * This parser reads user input and tries to interpret it as a "Zork"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parser {
	
	private static Command prevCommand;

	public static Command getCommand() {
		String inputLine = ""; // will hold the full input line
		String[] words;

		System.out.print("> "); // print prompt

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			inputLine = reader.readLine();
		} catch (java.io.IOException exc) {
			System.out.println("There was an error during reading: " + exc.getMessage());
		} 
		
		if (inputLine.equals("p")) {
			if (prevCommand != null) {
				return prevCommand;
			}
		}

		words = inputLine.split(" ");
		
		words = Command.removeDeterminers(words);

		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toLowerCase();
		}
		
		Command command = new Command(words);
		
		//command.check();
		prevCommand = command;
		return command;
	}

	/**
	 * Print out a list of valid command words.
	 */
	public static void showCommands() {
		CommandWords.showAll();
	}
	
}