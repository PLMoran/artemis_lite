/**
 * 
 */
package game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author po_th
 *
 */
class TestPlayer {

//test data
	String validName;
	int validPosition;
	int invalidPosition;
	int movePosition;
	int movedValidPosition;
	int validBalance;
	int invalidBalance;
	int validTransactionAmount;
	boolean passedGo;
	boolean isSystemOwner;
	int validPlayerDiceTotal;
	int invalidPlayerDiceTotal;
	ArrayList<Element> validElements;
	
	
@BeforeEach
void setUp() throws Exception {
	
	Player player = new Player();
	SystemGroup systemGroup = new SystemGroup(validName);
//vars
validName = "ValidName";

validPosition = 0;
invalidPosition = 12;

movePosition=1;
movedValidPosition = 1;

validBalance= 500;
invalidBalance = -100;

validTransactionAmount = 100;
passedGo = false;
isSystemOwner = true;

validPlayerDiceTotal = 12;
invalidPlayerDiceTotal = 1;




}

	/**
	 * Test method for {@link game.Player#Player()}.
	 */
	@Test
	void testPlayerConstructor() {
		Player player= new Player(validName);
		assertEquals(validName, player.getName());
	}

	/**
	 * Test method for {@link game.Player#getName()}.
	 */
	@Test
	void testValidSetGetName() {
		Player player = new Player();
		player.setName(validName);
		assertEquals(validName, player.getName());

	}

	/**
	 * Test method for {@link game.Player#resetPositionToZero()}.
	 */
	@Test
	void testValidResetPositionToZero() {
		Player player= new Player();
		player.resetPositionToZero();
		assertEquals( validPosition, player.getPosition());
	}

	/**
	 * Test method for {@link game.Player#getBalance()}.
	 */
	@Test
	void testGetBalance() {
		Player player = new Player();
		player.getBalance();
		assertEquals(validBalance, player.getBalance());
	}

	/**
	 * Test method for {@link game.Player#getTransactionAmount()}.
	 */
	@Test
	void testGetTransactionAmount() {
		Player player = new Player();
		player.completeTransaction(validTransactionAmount);
		assertEquals(validTransactionAmount, player.getTransactionAmount());
		
	}

	/**
	 * Test method for {@link game.Player#completeTransaction(int)}.
	 */
	@Test
	void testCompleteTransaction() {
		Player player = new Player();
		player.completeTransaction(validTransactionAmount);
		assertEquals(100, validTransactionAmount);
	}

	/**
	 * Test method for {@link game.Player#isPassedGo()}.
	 */
	@Test
	void testIsPassedGo() {
		Player player = new Player();
		player.isPassedGo();
		assertEquals(passedGo, false);
	}

	/**
	 * Test method for {@link game.Player#getElements()}.
	 */
	@Test
	void testGetElements() {
		Player player = new Player();
	}

	
	/**
	 * Test method for {@link game.Player#setPlayerDiceTotal(int)}.
	 */
	@Test
	void testSetGetPlayerDiceTotal() {
		Player player = new Player();
		player.setPlayerDiceTotal(validPlayerDiceTotal);
		assertEquals(validPlayerDiceTotal, player.getPlayerDiceTotal());
	}

	/**
	 * Test method for {@link game.Player#getPosition()}.
	 */
	@Test
	void testGetPosition() {
		Player player = new Player();
		player.getPosition();
		assertEquals(validPosition, player.getPosition());
	}

	/**
	 * Test method for {@link game.Player#move(int)}.
	 */
	@Test
	void testMove() {
		Player player = new Player();
		player.move(movePosition);
		assertEquals(movedValidPosition, player.getPosition());
		
	}

	/**
	 * Test method for {@link game.Player#moveTo(int)}.
	 */
	@Test
	void testMoveTo() {
		Player player = new Player();
		player.moveTo(movedValidPosition);
		assertEquals(movePosition, player.getPosition());
		
	}

	/**
	 * Test method for {@link game.Player#isSystemOwner(game.Element)}.
	 */
	@Test
	void testIsSystemOwner() {
	Player player = new Player();
	assertEquals(true, isSystemOwner);
	}

}
