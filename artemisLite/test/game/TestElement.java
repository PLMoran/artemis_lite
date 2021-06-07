/**
 * 
 */
package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author po_th
 *
 */
class TestElement {

	// test data
	static String nameValid;
	static int priceValid;
	static int upgradePriceValid;
	static int numUpgradesValid;
	static boolean isOwnedValid;
	static Player ownerValid;
	static int baseRentValid;
	static SystemGroup systemGroupValid;
	static Player playerValid = new Player();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		nameValid = "ValidElementName";
		priceValid = 100;
		upgradePriceValid = 100;
		numUpgradesValid = 1;
		isOwnedValid = true;
		ownerValid = playerValid;
		baseRentValid = 100;
		systemGroupValid = new SystemGroup("SystemGroupValid");

	}

	/**
	 * Test method for
	 * {@link game.Element#Element(java.lang.String, int, int, int, boolean, game.Player, int, game.SystemGroup)}.
	 */
	@Test
	void testElementConstructor() {
		Element element = new Element(nameValid, priceValid, upgradePriceValid,numUpgradesValid, isOwnedValid, ownerValid, baseRentValid, systemGroupValid);
		assertEquals(nameValid, element.getName());
		assertEquals(priceValid, element.getPrice());
		assertEquals(upgradePriceValid, element.getUpgradePrice());
		assertEquals(numUpgradesValid, element.getNumUpgrades());
		assertEquals(isOwnedValid, element.isOwned());
		assertEquals(ownerValid, element.getOwner());
		assertEquals(baseRentValid, element.getRent());
		assertEquals(systemGroupValid, element.getSystemGroup());
		
	}

	/**
	 * Test method for {@link game.Element#getPrice()}.
	 */
	@Test
	void testGetPrice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#getUpgradePrice()}.
	 */
	@Test
	void testGetUpgradePrice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#hasUpgrades()}.
	 */
	@Test
	void testHasUpgrades() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#canUpgrade()}.
	 */
	@Test
	void testCanUpgrade() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#getNumUpgrades()}.
	 */
	@Test
	void testGetNumUpgrades() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#setNumUpgrades(int)}.
	 */
	@Test
	void testSetNumUpgrades() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#isOwned()}.
	 */
	@Test
	void testIsOwned() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#setOwned(boolean)}.
	 */
	@Test
	void testSetOwned() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#getOwner()}.
	 */
	@Test
	void testGetOwner() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#setOwner(game.Player)}.
	 */
	@Test
	void testSetOwner() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#getRent()}.
	 */
	@Test
	void testGetRent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#getSystemGroup()}.
	 */
	@Test
	void testGetSystemGroup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Element#toString(int)}.
	 */
	@Test
	void testToStringInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Square#Square(java.lang.String)}.
	 */
	@Test
	void testSquare() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Square#getName()}.
	 */
	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.Square#toString()}.
	 */
	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
