/**
 * 
 */
package game;

import java.util.ArrayList;

/**
 * @author $Pat Moran 40044102
 *
 */
public class Element extends Square {

	public static int MAX_UPGRADES = 3;
	public static ArrayList<Element> fullyUpgradedElements = new ArrayList<Element>();

	// instance vars
	private int price;
	private int upgradePrice;
	private int numUpgrades;
	private boolean isOwned;
	private Player owner;
	private int baseRent;
	private SystemGroup systemGroup;

	// CONSTRUCTORS

	/**
	 * 
	 * @param name
	 * @param price
	 * @param upgradePrice
	 * @param currentUpgrades
	 * @param isOwned
	 * @param owner
	 * @param rentTable
	 * @param baseRent
	 */

	public Element(String name, int price, int upgradePrice, int numUpgrades, boolean isOwned, Player owner,
			int baseRent, SystemGroup systemGroup) {
		super(name);
		this.price = price;
		this.upgradePrice = upgradePrice;
		numUpgrades = 0;
		isOwned = false;
		owner = null;
		this.baseRent = baseRent;
		systemGroup.addSystem(this);
		this.systemGroup = systemGroup;

		return;
	}

	// Methods

	/**
	 * @return the price of the Element
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the upgradePrice for the element
	 */
	public int getUpgradePrice() {
		return upgradePrice;
	}

	/**
	 * Checks if Upgrades have been completed on an element
	 * 
	 * @return true if numUpgrades is greater than 0
	 * 
	 */
	public boolean hasUpgrades() {
		return numUpgrades > 0;
	}

	/**
	 * Checks if upgrades can be made on an element.
	 * 
	 * @return true if number of Upgrades is less than the Maximum Number of
	 *         Upgrades {@link #MAX_UPGRADES}
	 */
	public boolean canUpgrade() {
		return numUpgrades < MAX_UPGRADES;
	}

	/**
	 * @return the currentUpgrades
	 */
	public int getNumUpgrades() {
		return numUpgrades;
	}

	/**
	 * @param currentUpgrades the currentUpgrades to set
	 */
	public void setNumUpgrades(int numUpgrades) {
		this.numUpgrades = numUpgrades;
	}

	/**
	 * @return the isOwned
	 */
	public boolean isOwned() {
		return isOwned;
	}

	/**
	 * @param isOwned the isOwned to set
	 */
	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getRent() {
		int rent;
		if (numUpgrades == 0) {
			rent = baseRent;
		} else {
			rent = (baseRent + (baseRent * numUpgrades));
		}
		return rent;
	}
	
	public boolean isElementsFullyUpgraded() {
		if (numUpgrades == MAX_UPGRADES) {
		}
				return true;
	}

	/**
	 * 
	 * @return
	 */
	public SystemGroup getSystemGroup() {
		return systemGroup;
	}

	public String toString(int index) {
		return super.toString();
	}
	

}
