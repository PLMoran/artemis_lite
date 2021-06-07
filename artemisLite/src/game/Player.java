/**
 * 
 */
package game;

import java.util.ArrayList;

/**
 * @author $Pat Moran 40044102
 *
 */
public class Player {
	
	private static final int INITIAL_BALANCE = 500;

	// instance vars
	private String name;
	private int position;
	private int balance = INITIAL_BALANCE;
	private int transactionAmount;
	private boolean passedGo;
	private int playerDiceTotal;
	private ArrayList<Element> elements = new ArrayList<Element>();

	/**
	 * Default Constructor
	 */
	public Player() {
	}

	/**
	 * Constructor with Args
	 * 
	 * @param name
	 */
	public Player(String name) {
		super();
		this.name = name;
		this.position = 0;
		balance = INITIAL_BALANCE;
		passedGo = false;
		return;
	}

	// Methods
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Resets the Player's position to 0.
	 */
	public void resetPositionToZero() {
		this.position = 0;
	}

	/**
	 * 
	 * @return
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * 
	 * @return
	 */
	public int getTransactionAmount() {
		return transactionAmount;
	}
	
	public void completeTransaction(int transactionAmount) {
		balance = balance + transactionAmount;
		this.transactionAmount = transactionAmount;
		return;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPassedGo() {
		return passedGo;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Element> getElements() {
		return elements;
	}

	public int getPlayerDiceTotal() {
		return playerDiceTotal;
	}

	public void setPlayerDiceTotal(int playerDiceTotal) {
		this.playerDiceTotal = playerDiceTotal;
	}

	/**
	 * 
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * 
	 * @param squares
	 */
	public void move(int squares) {
		position = position + squares;
		if (position >= Board.NUM_SQUARES) {
			position = position - Board.NUM_SQUARES;
			passedGo = true;
		} else {
			passedGo = false;
		}

		if (position < 0) {
			position = position + Board.NUM_SQUARES;
		}
		return;
	}
	
	/**
	 * 
	 * @param square
	 */
	public void moveTo (int square) {
		if(square < position) {
			passedGo = true;
		}else {
			passedGo = false;
			
		}
		position = square;
		return;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void addElement (Element element) {
		element.setOwner(this);
		elements.add(element);
		return;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void removeElement (Element element) {
		elements.remove(element);
				
		return;
	}
	
	public boolean isSystemOwner (Element element) {
		boolean ownsAll = true;
		SystemGroup systemGroup = element.getSystemGroup();
		for (Element e : systemGroup.getElements()) {
			if (!e.isOwned() || (e.isOwned() && e.getOwner() != this))
				ownsAll = false;
		}
		return ownsAll;
	}
	
}