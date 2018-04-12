package zork.items;

/* 
 * Item class that contains the following:
 *     - id that describes what it is (apple, sword, shield)
 *     - consumable that describes whether the item is consumable
 */

public class Item {
	
	private String id;
	private boolean consumable;
	
	public Item(String id, boolean consumable) {
		this.id = id;
		this.consumable = consumable;
	}
	
	public Item() {}
	
	/*
	 * Returns the item's id
	 */
	public String getId() {
		return this.id;
	}
	
	/*
	 * Compares this items id with another's
	 */
	public boolean compareId(Item item) {
		if (this.id.equals(item.getId())) {
			return true;
		}
		return false;
	}
	
	/*
	 * Compares this items id with another id
	 */
	public boolean compareId(String id) {
		if (this.id.equals(id)) {
			return true;
		}
		return false;
	}
	
	/*
	 * Returns whether the item is consummable
	 */
	public boolean isConsumable() {
		return this.consumable;
	}
	
}
