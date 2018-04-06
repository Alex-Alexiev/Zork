package com.bayviewglen.zork;

public class Player {
	
	private Room currentRoom;
	private Parser parser;
	
	public Player(Room startingRoom) {
		currentRoom = startingRoom;
		parser = new Parser();
	}
	
	public void printLocation() {
		System.out.println(currentRoom.longDescription());
	}
	
	public void move(String direction) {
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
     * Given a command, process (that is: execute) the command.
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
        else if (commandWord.equals("quit"))
        {
            if(command.hasSecondWord())
                System.out.println("Quit what?");
            else
                return true;  // signal that we want to quit
        }else if (commandWord.equals("eat")){
        	System.out.println("Do you really think you should be eating at a time like this?");
        }
        return false;
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
    
    // implementations of user commands:

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        
        // Try to leave current room.
        move(direction);
        
    }
}
