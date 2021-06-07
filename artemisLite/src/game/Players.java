/**
 * 
 */
package game;

import java.util.ArrayList;

/**
 * @author $Pat Moran 40044102
 *
 */
public class Players {
	
	public static final int MIN_NUM_PLAYERS=2;
	public static final int MAX_NUM_PLAYERS = 4;
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	

	/*
	 * add Player to the Array List Player
	 */
	public void addPlayer (Player player) {
		players.add(player);
		return;
	}
	
	
}


