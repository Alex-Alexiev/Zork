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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CommandWords {

	private static HashMap<String, String[]> wordGroups = new HashMap<String, String[]>();
	private static ArrayList<String> mainCommands;
	private static ArrayList<String> allCommands;
	
	static {
		CommandWords.initVocab("data/vocabulary.dat");
	}
	
	public static void initVocab(String fileName) {
		Scanner vocabScanner;
		try {
			//make array of main command words 
			vocabScanner = new Scanner(new File(fileName));
			mainCommands = new ArrayList<String>();
			while (vocabScanner.hasNext()) {
				String firstLine = vocabScanner.nextLine();
				mainCommands.add(firstLine.split(":")[0]);
			}
			//make array of all valid command words
			vocabScanner = new Scanner(new File(fileName));
			allCommands = new ArrayList<String>();
			while (vocabScanner.hasNext()) {
				String firstLine = vocabScanner.nextLine();
				for (String word : firstLine.split(":")[1].trim().split(",")) {
					allCommands.add(word.trim());
				}
			}
			//make array of word synonyms 
			vocabScanner = new Scanner(new File(fileName));
			while (vocabScanner.hasNext()) {
				String firstLine = vocabScanner.nextLine();
				String key = firstLine.split(":")[0];
				String[] values = firstLine.split(":")[1].trim().split(",");
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].trim();
				}
				wordGroups.put(key, values);
			}
			vocabScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean is(String commandGroup, String command) {
		for (String s : wordGroups.get(commandGroup)) {
			if (s.equals(command)) {
				return true;
			}
		}
		return false;
	}

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
		for (int i = 0; i < allCommands.size(); i++) {
			if (allCommands.get(i).split(" ")[0].equals(aString))
				return true;
		}
		// if we get here, the string was not found in the commands
		return false;
	}

	/*
	 * Print all valid commands to System.out.
	 */
	public void showAll() {
		for (int i = 0; i < mainCommands.size(); i++) {
			System.out.print(mainCommands.get(i) + " | ");
			if (i % 8 == 0 && i != 0)
				System.out.println();
		}
		System.out.println();
	}
}
