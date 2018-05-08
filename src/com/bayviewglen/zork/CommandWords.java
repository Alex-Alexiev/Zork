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

class CommandWords
{
    // a constant array that holds all valid command words
    private static final String validCommands[] = {
        "go", "move", "walk", "run", "help", "quit", "search", "find", "look",
        "room", "place", "location", "spot", "equip", "equipment",
        "unequip", "welfare", "eat", "consume", "bite", "chew", "ingest",
        "snack", "munch", "gorge", "dine", "lunch", "take",
        "attack *monster name* with *weapon name*", 
        "drop", "inventory", "items", "things", "supply"
    };
    
    private static final String locationCommands[] = {
    		"room", "place", "location", "spot"
    };
    
    private static final String moveCommands[] = {
    		"go", "move", "walk", "run"
    };
    
    private static final String searchCommands[] = {
    		"search", "find", "look"
    };
    
    private static final String inventoryCommands[] = {
    		"inventory", "items", "things", "supply"
    };
    
    private static final String eatCommands[] = {
    		"eat", "consume", "bite", "chew", "ingest",
    		"snack", "munch", "gorge", "dine", "lunch"
    };
    
    private static final String pickupCommands[] = {
    		"",
    };
    
    private static final String equipmentCommands[] = {
    		"",
    };
    
    private static final String unequipCommands[] = {
    		"",
    };
    
    private static final String quitCommands[] = {
    		"",
    };
    
    
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * Return true if it is, false if it isn't.
     **/
    public static boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++)
        {
            if(validCommands[i].split(" ")[0].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /*
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(int i = 0; i < validCommands.length; i++)
        {
            System.out.print(validCommands[i] + " | ");
            if (i % 8 == 0 && i != 0) 
            	System.out.println();
        }
        System.out.println();
    }
    
    /* 
     * Checks location commands
     */
    public static boolean isLocationCommand(String command) {
    	for (String validCommand : locationCommands) {
    		if (validCommand.equals(command))
    			return true;
    	}
    	return false;
    }
    
    /* 
     * Checks move commands
     */
    public static boolean isMoveCommand(String command) {
    	for (String validCommand : moveCommands) {
    		if (validCommand.equals(command))
    			return true;
    	}
    	return false;
    }
    
    /* 
     * Checks search commands
     */
    public static boolean isSearchCommand(String command) {
    	for (String validCommand : searchCommands) {
    		if (validCommand.equals(command))
    			return true;
    	}
    	return false;
    }
    
    /* 
     * Checks inventory commands
     */
    public static boolean isInventoryCommand(String command) {
    	for (String validCommand : inventoryCommands) {
    		if (validCommand.equals(command))
    			return true;
    	}
    	return false;
    }
    
    /* 
     * Checks eat commands
     */
    public static boolean isEatCommand(String command) {
    	for (String validCommand : eatCommands) {
    		if (validCommand.equals(command))
    			return true;
    	}
    	return false;
    }
    
    /* 
     * Checks pickup commands
     */
    public static boolean isPickupCommand(String command) {
    	for (String validCommand : pickupCommands) {
    		if (validCommand.equals(command))
    			return true;
    	}
    	return false;
    }
}