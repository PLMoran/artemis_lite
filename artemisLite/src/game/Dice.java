/**
 * 
 */
package game;

/**
 * @author Pat Moran 40044102
 * This class implements the IDice.java interface and creates an array of 2 dice.
 * When Rolled this will return two values, a combined total, and if the roll was a double.
 */
public class Dice implements IDice{
	
	
	private static final int NUM_DICE = 2;
	
	
	private int[] dice = new int[NUM_DICE];
	
	/**
	 *  Dice Roll
	 *  Iterates through array of dice, and allocates 1-6 with each dice
	 */
	public void roll() {
		for (int loop=0; loop<NUM_DICE;  loop++ ) {
			dice[loop] = 1 + (int)(Math.random()*6);
		}
		return;
	}
	
	/**
	 * gets the dice allocated values from roll()
	 * returns the dice array
	 */
	@Override
	public int[] getDice() {
		return dice;
	}
	
/**
 * returns the total value of the dice Array
 */
	@Override
	public int getTotal() {
		int diceTotal = 0;
		for (int loop = 0 ; loop < dice.length; loop++ ) {
			diceTotal += dice[loop];
		}
		return diceTotal;
	}
	
/**
 * Returns if dice rolled was a double
 * Allows for take another turn, if implemented.
 */
	@Override
	public boolean isDouble() {
		// TODO Auto-generated method stub
		return dice[0] == dice[1];
	}
	/**
	 * returns the value of individual rolls.
	 */
	@Override
	public String toString() {
		return "Dice 1 rolled: " + dice[0] + "\nDice 2 rolled: " + dice[1];

	}
	
}