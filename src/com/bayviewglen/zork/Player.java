package com.bayviewglen.zork;
import com.bayviewglen.zork.items.Item;

public class Player {
	
	private Room currentRoom;
	private Parser parser;
	
	private Inventory inventory;
		
	public Player(Room startingRoom) {
		currentRoom = startingRoom;
		parser = new Parser();
		inventory = new Inventory();
	}
	
	/**
     * Parse command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public boolean act() 
    {
    	Command command = parser.getCommand();
    	
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("where"))
        	printLocation();
        else if (commandWord.equals("inventory"))
        	printInventory();
        else if (commandWord.equals("quit"))
        {
            if (command.hasManyWords(2))
                System.out.println("Quit what?");
            else
                return true;  // signal that we want to quit
        }
        else if (commandWord.equals("eat")){
        	System.out.println("Do you really think you should be eating at a time like this?");
        }
        else if (commandWord.equals("take")) {
        	pickupItem(command);
        }
        else if (commandWord.equals("attack")) {
        	//
        }
        else if (commandWord.equals("drop")) {
        	dropItem(command);
        }
        
        return false;
    }
    
    /**
	 * Prints location of the player
	 */
	public void printLocation() {
		System.out.println(currentRoom.longDescription());
	}
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at Monash Uni, Peninsula Campus.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * Prints inventory
     */
    private void printInventory() {
    	System.out.println("\nYou have:\n" + inventory);
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasManyWords(2))
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getWordAtIndex(2);
        
        // Try to leave current room.
        move(direction);
    }
    
    /**
	 * Moves character to next room
	 * @param direction
	 */
	private void move(String direction) {
		Room nextRoom = currentRoom.nextRoom(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else 
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.longDescription());
        }
	}
	
	/**
	 * Drops an item
	 * @param command
	 */
	private void dropItem(Command command) {
		if(!command.hasManyWords(2))
        {
            System.out.println("\nDrop what?\n");
            return;
        }
		
        String itemId = command.getWordAtIndex(2);
        Item item = inventory.getItem(itemId);
        
        if (item != null) {
        	
        	System.out.println();
        	
        	if (item.isConsumable()) {
        		int amount = item.getAmount();
        		if (amount > 1)
        			System.out.println("You dropped " + amount + " " + itemId + "s.");
        		else 
        			System.out.println("You dropped " + amount + " " + itemId);
        	} else {
        		System.out.println("You dropped your" + itemId);
        	}
        	currentRoom.inventory.addToInventory(item, item.getAmount());
        	inventory.removeItem(itemId);
        } else {
        	System.out.println("\nYou have no " + itemId);
        }
        
        System.out.println("\nThe room has \n" + currentRoom.inventory);
        
	}
	
	/**
	 * Picks up an item
	 * @param command
	 */
	private void pickupItem(Command command) {
		if(!command.hasManyWords(2))
        {
            System.out.println("\nPickup what?\n");
            return;
        }
		
        String itemId = command.getWordAtIndex(2);
        Item item = currentRoom.inventory.getItem(itemId);
        
        if (item != null) {
        	
        	System.out.println();
        	
        	inventory.addToInventory(item, item.getAmount());
        	currentRoom.inventory.removeItem(itemId);
        	
        	if (item.isConsumable()) {
        		int amount = item.getAmount();
        		if (amount > 1)
        			System.out.println("You picked up " + amount + " " + itemId + "s.");
        		else 
        			System.out.println("You picked up " + amount + " " + itemId);
        	} else {
        		System.out.println("You picked up a " + itemId);
        	}
        } else {
        	System.out.println("\nThe room has no " + itemId);
        }
        
        System.out.println("\nThe room has \n" + currentRoom.inventory);
        
	}
}