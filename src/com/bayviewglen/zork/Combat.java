
package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.entity.Entity;
import com.bayviewglen.zork.entity.Monster;
import com.bayviewglen.zork.entity.Player;
import com.bayviewglen.zork.entity.monsters.HeartOfTheIsland;
import com.bayviewglen.zork.entity.monsters.SulfuricCrawler;
import com.bayviewglen.zork.items.Weapon;
import com.bayviewglen.zork.items.weapons.Bow;
import com.bayviewglen.zork.items.weapons.LegsOfLass;

/*
 * This class is used to let the player fight any number of enemies (Entities)
 * The class is controlled from the player class
 */

public class Combat {
	
	private ArrayList<Entity> enemies;
	private Player player;

	public Combat(Player player, ArrayList<Entity> enemies) {
		this.enemies = enemies;
		this.player = player;
	}
	
	/*
	 * A useful dialogue to decide whether a player wants to engage in combat
	 */
	public boolean chooseEngage() {
		System.out.println("Monsters stand in the way:");
		for (Entity e : enemies) {
			System.out.println(e);
		}
		System.out.println();
		System.out.println("Do you choose to fight this monster? If you  not to fight, you will return to the next room\n");
		Command response = Parser.getCommand();
		System.out.println();
		if (response.is("yes") || response.is("fight")) {
			return true;
		} else {
			System.out.println("You return to the previous room");
		}

		return false;
	}
	
	/*
	 * A loop that cycles between the player attacking and the monsters attacking
	 * This is ended when all the monster's die, when the player dies, or when the player leaves
	 */
	public boolean engageInCombat() {

		player.combat(true);

		Sound.stop();
		Sound mainThemeMusic = new Sound("data\\battle.wav");
		mainThemeMusic.loop();

		if (Math.random() >= 0.5 && !(player.getWeapon() instanceof Bow)) {
			enemiesAttack();
		}

		while (enemies.size() > 0 && player.getHealth() > 0) {
			setPreviousHealths();
			printStats();
			if (!playerAttack()) {
				System.out.println("Back to the previous room");
				combatEnded();
				return false;
			}
			removeDeadEnemies();
			enemiesAttack();
			player.turnEnded();
		}
		if (enemies.size() <= 0) {
			combatEnded();
			System.out.println("The monsters are slain\n");
			return true;
		} else {
			combatEnded();
			System.out.println("Oops, try again next time...");
		}
		return false;
	}

	private void restartMainMusic() {
		Sound.stop();
		Sound mainThemeMusic = new Sound("data\\mainmusic.wav");
		mainThemeMusic.loop();
	}
	
	private void combatEnded() {
		restartMainMusic();
		player.setDamageScaler(1);
		player.combat(false);
	}

	/*
	 * Enemies attack
	 */
	private void enemiesAttack() {
		for (Entity e : enemies) {
			// Damages monsters with poison
			e.poison();

			// Attacks player
			if (!(e instanceof Monster && ((Monster) e).isStunned())) {
				e.attack(player);
			}
		}
		System.out.println();
	}

	/*
	 * Prints the stats of the player and all monsters
	 */
	private void printStats() {
		System.out.println(player);
		System.out.println();
		for (Entity e : enemies) {
			System.out.println(e.getName() + " health: " + e.getHealth());
			if (e.isPoisoned()) {
				System.out.println(e.getName() +" is bleeding for " + e.getPoisonDamage() + "damage per turn");
			}
		}
		System.out.println();
	}

	/*
	 * Player attacks the monster of their choosing
	 */
	private boolean playerAttack() {

		System.out.println("Which monster would you like to attack?");
		System.out.println();
        Command command = null;

        while (2>1) {
            command = Parser.getCommand();

            player.act(command);

            if (command.is("leave")) {
                System.out.println("You barely escaped!");
                return false;
            }
            
            if (player.wantsToAttack()) {
                if (command.numOfWords() < 2) {
                    System.out.println("Attack what?");  
                }
                // Legs of Lass special case
                Weapon weapon = player.getWeapon();
                if (weapon instanceof LegsOfLass) {
                    int monstersLength = enemies.size();
                    for (Entity e : enemies) {
                        ((LegsOfLass) weapon).ability(e, monstersLength, player);
                    }
                    return true;
                }

                // Normal attack
                String monsterId = findMonsterId(command);
                if (monsterId != null) {
                    for (Entity e : enemies) {
                        if (e.getId().equals(monsterId.trim().toLowerCase())) {
                            player.attack(e);
                            return true;
                        }
                    }
                }
                else if (monsterId == null && !(command.numOfWords() < 2)) {
                    System.out.println("No enemy by that name, try again!");
                }
            }
            
            System.out.println();
        }
	}
	
	/*
	 * Decides which monster to attack based on the command the player entered
	 */
	private String findMonsterId(Command playerCommand) {
		String merged = Command.mergeFinalWords(playerCommand, 0);
		String[] monsters = CommandWords.getRelatedWords("monsters");
		for (String monster : monsters) {
			if (merged.indexOf(monster.trim()) != -1)
				return monster;
		}
		return null;
	}
	
	/*
	 * This is primarily for the BloodPriest 
	 * Previous health is the health at the beginning of the turn
	 */
	private void setPreviousHealths() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i) instanceof Monster) {
				Monster m = (Monster) enemies.get(i);
				m.setPrevHealth(m.getHealth());
			}
		}
	}

	/*
	 * Removes all dead monsters from the list enemies
	 */
	private void removeDeadEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHealth() <= 0) {
				if (enemies.get(i) instanceof SulfuricCrawler) {
					((SulfuricCrawler) enemies.get(i)).deadMove(player);
				}
				if (enemies.get(i) instanceof HeartOfTheIsland) {
					System.out.println("Congratulations! You have beaten the Heart Of The Island");
					System.out.println("Peace is restored so you sit back on a rock and crack open a cold one");
					System.out.println("T H E E N D");
					System.exit(0);
				}
				enemies.remove(i);
				i--;
			}
		}
	}

}