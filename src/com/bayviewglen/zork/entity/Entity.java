package com.bayviewglen.zork.entity;

import java.util.ArrayList;

import com.bayviewglen.zork.Poison;

/*
 * Any character in the game extends the entity class, including
 * monsters, NPC's, and even the player
 * All NPC's have an id, a name, health, and can be poisoned
 */

public abstract class Entity {

	private String id;
	private String name;
	protected int health;
	protected boolean stunned;
	private ArrayList<Poison> poisons = new ArrayList<Poison>();

	public Entity(String name) {
		this.name = name;
		this.id = name.replaceAll("\\s", "").toLowerCase();
	}
	
	/*
	 * Update function
	 */
	public void update() {
		poison();
	}


	/*
	 * Damage methods
	 */
	public abstract void attack(Entity e);

	public void damage(int hitPoints) {
		health -= hitPoints;
	}

	/*
	 * Poison
	 */
	public void addPoison(Poison poison) {
		String verb = "has";
		if (this instanceof Player)
			verb = "have";
		System.out.println(this.getName() + " " + verb + " been poisoned with "+poison.getId()+"!" );
		if (poison.isFinalAction()) {
			System.out.println("Lose "+poison.getDamage()+" health after "+poison.getLength()+" moves.");
		} else {
			System.out.println("Lose "+poison.getDamage()+" health after each turn for "+poison.getLength()+" turns.");
		}
		this.poisons.add(poison);
	}

	public boolean hasRabies() {
		for (Poison p : poisons) {
			if (p.getId().equals("rabies")) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * When an entity is poisoned the amount of damage is determined 
	 * by the poison it has 
	 * This poisons the entity from every poison that it has
	 */
	public void poison() {
		int index = 0;
		while (index < poisons.size()) {
			Poison poison = poisons.get(index);
			damage(poison.damage());
			if (!poison.isFinalAction()) {
				if (poison.isApplied()) {
					index++;
				} else {
					poisons.remove(index);
				}
			} else { //if it is Rabies
				if (poison.isApplied()) {
					poisons.remove(index);
				} else {
					index++;
				}
			}
		}
	}

	/*
	 * Getters
	 */
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getPoisonDamage() {
		int poisonDamage = 0;
		for (Poison poison : poisons) {
			poisonDamage += poison.getDamage();
		}
		return poisonDamage;
	}
	
	public int getRabiesTicks() {
		for (Poison p : poisons) {
			if (p.getId().equals("rabies")) {
				return p.getLength();
			}
		}
		return 0;
	}

	public boolean isPoisoned() {
		if (poisons.size() > 0)
			return true;
		return false;
	}

	public boolean isStunned() {
		return stunned;
	}

	/*
	 * Setters
	 */
	public void stun(boolean s) {
		stunned = s;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String toString() {
		return name;
	}
}
