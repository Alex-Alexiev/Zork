package com.bayviewglen.zork;

import java.util.ArrayList;

/**
 * Class Command - Part of the "Zork" game.
 * 
 * author: Michael Kolling version: 1.0 date: July 1999
 *
 * This class holds information about a command that was issued by the user. A
 * command currently consists of two strings: a command word and a second word
 * (for example, if the command was "take map", then the two strings obviously
 * are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid command
 * words. If the user entered an invalid command (a word that is not known) then
 * the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 *
 * The second word is not checked at the moment. It can be anything. If this
 * game is extended to deal with items, then the second part of the command
 * should probably be changed to be an item rather than a String.
 */

public class Command {
	/*
	 * Command words is the first would in this array
	 */
	private String[] words;

	/**
	 * Create a command object. First and second word must be supplied, but either
	 * one (or both) can be null. The command word should be null to indicate that
	 * this was a command that is not recognised by this game.
	 */
	public Command(String[] words) {
		this.words = words;
	}
	
	/*
	 * Checks whether the command is of a specified type such as a "move" command
	 * or a "pick up" command
	 * It uses the CommandWords class which manages the vocabulary data file 
	 */
	public boolean is(String word) {
		return CommandWords.is(word, getCommandWord());
	}
	
	/*
	 * Checks if the command has a keyword or any synonym of that keyword
	 */
	public boolean has(String keyWord) {
		for (String w: words) {
			if (CommandWords.is(keyWord, w)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the command word (the first word) of this command. If the command was
	 * not understood, the result is null.
	 */
	public String getCommandWord() {
		if (words.length > 0) {
			return words[0];
		}
		return null;
	}

	/**
	 * Return the command word (the first word) of this command. If the command was
	 * not understood, the result is null.
	 */
	public String getSecondWord() {
		return words[1];
	}

	/**
	 * Return the second word of this command. Returns null if there was no second
	 * word.
	 */
	public String getWordAtIndex(int i) {
		return words[i];
	}

	/**
	 * Return true if this command was not understood.
	 */
	public boolean isUnknown() {
		return (words[0] == null) || !CommandWords.isCommand(words[0]);
	}

	/**
	 * Return true if the command has a second word.
	 */
	public boolean hasManyWords(int i) {
		return (words.length >= i);
	}

	/**
	 * Returns how many command words
	 */
	public int numOfWords() {
		return words.length;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < words.length; i++) {
			ret += words[i] + ", ";
		}
		return ret;
	}
	
	/*
	 * returns all the words past index i inclusive merged into one string
	 */
	public static String mergeFinalWords(Command command, int index) {
		String ret = "";
		for (int i = index; i < command.numOfWords(); i++) {
			ret += command.getWordAtIndex(i);
		}
		return ret.trim();
	}
	
	/*
	 * returns all the commands after index i as one string with spaces
	 */
	public static String formatFinalWords(Command command, int index) {
		String ret = "";
		for (int i = index; i < command.numOfWords(); i++) {
			ret += command.getWordAtIndex(i) + " ";
		}
		return ret.trim();
	}
	
	public void generalize() {
		for (int i = 0; i < words.length; i++) {
			words[i] = CommandWords.getWordKey(words[i]);
		}
	}

	public static String[] removeDeterminers(String[] words) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {
			if (!CommandWords.is("determiner", words[i])) {
				ret.add(words[i]);
			}
		}
		String[] retArr = new String[ret.size()];
		for (int i = 0; i < retArr.length; i++) {
			retArr[i] = ret.get(i);
		}
		return retArr;
	}
}
