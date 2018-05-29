package com.bayviewglen.zork;
/**
 * Class Command - Part of the "Zork" game.
 * 
 * author:  Michael Kolling
 * version: 1.0
 * date:    July 1999
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 *
 * The second word is not checked at the moment. It can be anything. If this
 * game is extended to deal with items, then the second part of the command
 * should probably be changed to be an item rather than a String.
 */

public class Command
{
	/*
	 * Command words is the first would in this array
	 */
    private String[] words;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognised by this game.
     */
    public Command(String[] words)
    {
        this.words = words;
    }
    
    public boolean is(String word) {
    	return CommandWords.is(word, getCommandWord());
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     */
    public String getCommandWord()
    {
        return words[0];
    }
    
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     */
    public String getSecondWord()
    {
        return words[1];
    }

    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     */
    public String getWordAtIndex(int i)
    {
        return words[i];
    }

    /**
     * Return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (words[0] == null) || !CommandWords.isCommand(words[0]);
    }

    /**
     * Return true if the command has a second word.
     */
    public boolean hasManyWords(int i)
    {
        return (words.length >= i);
    }
    
    /**
     * Reutrns how many command words
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
    
    public static String mergeFinalWords(Command command, int index) {
    	String ret = "";
    	for (int i = index; i < command.numOfWords(); i++) {
    		ret += command.getWordAtIndex(i) + " ";
    	}
    	return ret.trim();
    }
}
