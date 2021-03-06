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

/*
 * The class reads data from the vocabulary data file. That file
 * stores the main command words along with any accepted synonyms.
 * This class features mostly static methods that are used to check
 * the validity of commands
 */

public class CommandWords {
	
	//stores the main command word with all its valid synonyms
	private static HashMap<String, String[]> wordGroups = new HashMap<String, String[]>();
	
	//stores the main command words 
	private static ArrayList<String> mainCommands;
	
	//stores all the commands
	private static ArrayList<String> allCommands;

	static {
		CommandWords.initVocab("data/vocabulary.dat");
	}

	public static void initVocab(String fileName) {
		Scanner vocabScanner;
		try {
			// make array of main command words
			vocabScanner = new Scanner(new File(fileName));
			mainCommands = new ArrayList<String>();
			while (vocabScanner.hasNext()) {
				String firstLine = vocabScanner.nextLine();
				mainCommands.add(firstLine.split(":")[0]);
			}
			// make array of all valid command words
			vocabScanner = new Scanner(new File(fileName));
			allCommands = new ArrayList<String>();
			while (vocabScanner.hasNext()) {
				String firstLine = vocabScanner.nextLine();
				for (String word : firstLine.split(":")[1].trim().split(",")) {
					allCommands.add(word.trim());
				}
			}
			// make array of word synonyms
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
	
	//returns all the synonyms of a given word
	public static String[] getRelatedWords(String key) {
		return wordGroups.get(key);
	}
	
	/*
	 * returns the main command for a given synonym
	 */
	public static String getWordKey(String word) {
		for (HashMap.Entry<String, String[]> mapEntry : wordGroups.entrySet()) {
			for (String s : mapEntry.getValue()) {
				if (s.equals(word)) {
					return mapEntry.getKey();
				}
			}
		}
		return null;
	}
	
	/*
	 * checks whether the given String command is a synonym of the main command commandGroup
	 */
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
		for (String command : allCommands) {
			if (command.equals(aString)) return true;
		}
		// if we get here, the string was not found in the commands
		return false;
	}

	/*
	 * Print all valid commands to System.out.
	 */
	public static void showAll() {
		int i = 0;
		while (!mainCommands.get(i).trim().equals("determiner")) {
			System.out.print(mainCommands.get(i) + " | ");
			if (i % 8 == 0 && i != 0)
				System.out.println();
			i++;
		}
		System.out.println();
	}
	
}
