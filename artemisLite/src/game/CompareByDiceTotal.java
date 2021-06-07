package game;

import java.util.Comparator;

/**
 * 
 * @author Chloe Donnan 40174510
 * 
 * Comparator for Player attribute playerDiceTotal. 
 * Will sort a list of players by their dice total, starting from the highest.
 *
 */
public class CompareByDiceTotal implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		return p2.getPlayerDiceTotal() - p1.getPlayerDiceTotal();
	}

	
	
}