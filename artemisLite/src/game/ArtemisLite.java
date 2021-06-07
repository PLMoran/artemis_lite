
package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author $Pat Moran 40044102
 * @author Chloe Donnan 40174510
 * @author Matthew White 40202047
 * @author orlam 40079205
 */
public class ArtemisLite {

	private static final int GO_MONEY = 100;

	private Players players = new Players();
	private Player currentPlayer;
	private Dice dice = new Dice();
	private Board board = new Board(dice);
	Scanner scanner = new Scanner(System.in);

	private boolean quitGame = false;
	private boolean bankrupt = false;
	private boolean allElementsUpgraded = false;
	private boolean gameOver = false;
	private boolean passedGo = false;
	Player holdCurrentPlayer = null;
	Player auctionPlayer = null;

	int numOfPlayers;
	int indexToGet;
	int elementIndexToGet;
	int userTradeOption;
	int elementTradeOption;
	int playerIndex;
	int elementIndex;

	public void startGame() {
		int startGameOption;
		System.out.println();
		System.out.println("Would you like to begin?");
		System.out.println("Please Enter 1 or 2");
		System.out.println("1. Yes, Start Game!");
		System.out.println("2. Read Game Play Rules!");
		startGameOption = scanner.nextInt();
		try {

			switch (startGameOption) {
			case 1:
				return;

			case 2:
				readRules();
				break;

			default:

			}
		} catch (InputMismatchException e) {
			System.err.println("Please only enter a number into this menu");

			scanner.next();

			startGame();
		}
	}

	public void readRules() {

		System.out.println("Game Mechanics:");
		System.out.println("Selecting Menu Options:");
		System.out.println("During the Players Turn, they will be given a list of menu options."
				+ "\nOn your keyboard enter the number value for your chosen option "
				+ "\ne.g. for Option 1. enter '1' and press the 'Enter' Key.");

		System.out.println("\nObjectives:");
		System.out.println("The object of ArtemisLite is to co-operatively develop a complete Space Program "
				+ "\nto land humans on the moon by 2024. "
				+ "\nTo do this, players must Invest in Elements of a System relating to a differnet "
				+ "\nsection of the Space Program. "
				+ "\n\nEach Element must be Upgraded 3 times to be fully completed. "
				+ "\nTo Upgrade and Element, all Elements within a System must be Owned by a player. "
				+ "\nIf multiple players own Elements within the same System, Systems may be "
				+ "\nTraded between players. "

				+ "\n\nTo win the game, all 10 Elements, within 4 Systems must be Owned and fully Upgraded. "
				+ "\n\nIf a Player runs out of Money and is Bankrupt, the Game will End, "
				+ "\nwith the mission a failure.");

		startGame();
	}

	/**
	 * Sets up Game Player Numbers Validation for Input Mismatch Exception- will
	 * loop if non integer between {@link Players#MIN_NUM_PLAYERS} &
	 * {@link Players#MAX_NUM_PLAYERS}
	 */
	public void setPlayerNumbers() throws InputMismatchException {
		System.out.println("Please enter the number of players (Between 2-4)");

		try {
			numOfPlayers = scanner.nextInt();
			if (numOfPlayers >= Players.MIN_NUM_PLAYERS && numOfPlayers <= Players.MAX_NUM_PLAYERS) {
				System.out.println("Number of players is: " + numOfPlayers);
			} else {
				System.err.println("Please only enter a number between 2 and 4.");
				setPlayerNumbers();
			}

		} catch (InputMismatchException e) {
			System.err.println("Please enter a valid Number of Players between " + Players.MIN_NUM_PLAYERS + " and "
					+ Players.MAX_NUM_PLAYERS);
			scanner.next();

			setPlayerNumbers();
		}

	}

	/**
	 * Sets up game player names adds Players to Array List of Players class
	 */

	// Validation for player names no duplicates
	public void setPlayerNames() {
		System.out.println();
		System.out.println("Please enter the name of each player");

		// Sets up player 1
		String input = scanner.next();
		Player player = new Player();

		player.setName(input);
		System.out.println("Player 1 name accepted.");
		players.addPlayer(player);

		// Loops through the remaining number of players
		for (int i = 2; i <= numOfPlayers; i++) {

			// checks if there is at least one name within the array of players
			if (i > 0) {

				input = scanner.next();

				while (nameIsDuplicated(Players.players, input)) {

					System.err.println("This name has already been entered. Please enter a different name.");
					input = scanner.next();

				}

				System.out.println("Player " + i + " name accepted.");
				player = new Player();
				player.setName(input);
				players.addPlayer(player);

			}
		}

	}

	/**
	 * Checks if player name already exists within the arrayList of platers
	 * 
	 * @param players
	 * @param input
	 * @return nameIsDuplicated
	 */
	public boolean nameIsDuplicated(ArrayList<Player> players, String input) {

		boolean nameIsDuplicated = false;

		for (Player player : players) {

			if (player.getName().trim().equalsIgnoreCase(input)) {
				nameIsDuplicated = true;
			}

		}

		return nameIsDuplicated;

	}

	/**
	 * Dice will be rolled for each player. Player order will be decided by heighest
	 * dice in descending order.
	 */
	public void decideStarter() {

		int highestRoll = 0;

		System.out.println();
		System.out.println("Rolling Dice to see who goes first...");
		for (Player player : Players.players) {

			dice.roll();

			player.setPlayerDiceTotal(dice.getTotal());

			if (dice.getTotal() > highestRoll) {
				highestRoll = dice.getTotal();
				// Current player initially set to first player
				currentPlayer = player;
				player.setPlayerDiceTotal(dice.getTotal());
			} else if (dice.getTotal() == highestRoll) {
				while (dice.getTotal() == highestRoll) {
					dice.roll();
					player.setPlayerDiceTotal(dice.getTotal());
				}

			}

			// Display dice numbers for each player
			System.out.println(player.getName() + "'s dice total: " + dice.getTotal());

		}

		// Sort players by their dice roll total
		Collections.sort(Players.players, new CompareByDiceTotal());
		System.out.println("\nPlayer order: ");

		// Display ordered list of player names
		for (Player player : Players.players) {
			System.out.println(player.getName());
		}

		// display first player
		System.out.println("\nThe first player is " + currentPlayer.getName());

	}

	/**
	 * Central method to the Turn based system of the game. Will move player to a
	 * square based on diceRoll. Will give them the option to Invest if unowned.
	 * Will call displayMenu, which will allow upgrade, endTurn or Quit methods to
	 * be called.
	 */
	public void takeTurn() {

		holdCurrentPlayer = currentPlayer;

		System.out.println();
		System.out.println(currentPlayer.getName() + "'s turn");

		System.out.println();
		System.out.println(currentPlayer.getName() + " rolls the dice...");

		// calls dice roll method
		dice.roll();
		System.out.println();
		System.out.println(dice.toString());
		System.out.println(currentPlayer.getName() + "'s dice total: " + dice.getTotal());

		// moves player
		currentPlayer.move(dice.getTotal());

		// checks if player has passed go
		passedGo();

		// displays where the player has arrived at
		System.out.println();
		System.out.println(currentPlayer.getName() + " has arrived at " + board.getSquare(currentPlayer.getPosition()));

		// Player should be offered opportunity to Invest in element upon arrival,
		// avoids
		// them ending turn without other players given opportunity to invest in a site.
		buyElement();

		if (isBankrupt() == true) {
			gameOver();
		}

		if (isAllElementsUpgraded() == true) {
			displayWin();
			System.exit(0);
		}

		// display turn menu
		displayMenu();
	}

	/**
	 * After Player Movement is complete passedGo is called, checks if player has
	 * passed Go square[index 0] and updates their balance with new amount.
	 */
	public void passedGo() {
		if (currentPlayer.isPassedGo()) {
			currentPlayer.completeTransaction(+GO_MONEY);
			System.out.println(currentPlayer.getName() + " has Passed Go.");
			System.out.println("They have collected new Funding of $" + GO_MONEY);
			System.out.println(currentPlayer.getName() + "'s new balance of $" + currentPlayer.getBalance());
		}
	}

	/**
	 * Displays the menu options for purchasing an element
	 * 
	 * @throws InputMismatchException
	 */
	public void buyElement() throws InputMismatchException {
		// if squares landed on is an element i.e not go or free space
		if (board.getSquare(currentPlayer.getPosition()) instanceof Element) {

			Element element = (Element) board.getSquare(currentPlayer.getPosition());
			// if element is not owned
			if (!element.isOwned()) {
				// offer choice to purchase element
				System.out.println();
				System.out.println("Cost to invest in " + element + " is: $" + element.getPrice());
				System.out.println("\nWould you like to invest in this element?");

				int buyOption;

				try {

					do {

						System.out.println("\nEnter option: ");
						System.out.println("1. Display Balance");
						System.out.println("2. Invest in Element");
						System.out.println("3. Don't Invest. Offer to other players for Investment.");
						System.out.println("4. No player to invest in Element.");

						buyOption = scanner.nextInt();

						switch (buyOption) {
						case 1:
							System.out.println("Current Balance is: $" + currentPlayer.getBalance());
							break;
						case 2:
							investElement();

							break;

						case 3:
							if (auctionPlayer == holdCurrentPlayer) {
								System.out.println("Nobody has decided to Invest in this System");
								displayMenu();
							} else {
								auctionElement();
							}
							break;

						case 4:
							System.out.println("Element will remain unowned for now.");
							break;
						default:

						}

					} while (buyOption != 2);

				} catch (InputMismatchException e) {
					System.err.println("Please only enter a number into this menu");

					scanner.next();

					buyElement();
				}

			} else {
				System.out.println("Element has already been invested in by " + element.getOwner().getName());
				payRent();
			}
		} else {
			// message to user if they are at a blank square
			System.out.println("This square cannot be purchased.");
		}

		currentPlayer = holdCurrentPlayer;

	}

	/**
	 * Method to invest in the Current unowned Element occupied by the player
	 */
	public void investElement() {
		Element element = (Element) board.getSquare(holdCurrentPlayer.getPosition());

		if (currentPlayer.getBalance() >= element.getPrice()) {
			currentPlayer.completeTransaction(-element.getPrice());
			currentPlayer.addElement(element);

			element.setOwned(true);

			System.out.println(element.getName() + " has been invested in by " + currentPlayer.getName() + ".");
		} else {
			System.out.println("Insufficient Balance.");
			System.out.println("Investing now would lead to Bankruptcy");
		}
	}

	/**
	 * Pass control to next player for opportunity to invest in unowned Element.
	 */
	public void auctionElement() {

		System.out.println(currentPlayer.getName() + " has decided not to Invest.");
		currentPlayer = Players.players.remove(0);
		System.out.println();

		Players.players.add(currentPlayer);
		currentPlayer = Players.players.get(0);

		auctionPlayer = currentPlayer;
		System.out.println();
		System.out.println(auctionPlayer.getName() + " has the option to Invest.");

	}

	/**
	 * Allows a player to upgrade an element owned by them within a system that is
	 * fully owned.
	 */
	public void upgradeElement() {

		// Show numbered list of elements owned by currentPlayer, number of upgrades,
		// and upgrade price
		System.out.println("\nYour current elements and upgrades:");
		int counter = 1;
		for (Element element : currentPlayer.getElements()) {
			System.out.println(counter + ". " + element + " (upgraded " + element.getNumUpgrades() + " times).");
			System.out.println("Cost to upgrade this element: $" + element.getUpgradePrice() + ".\n");
			counter += 1;
		}

		int menuOption;
		try {
			do {

				// Options for upgrading elements
				System.out.println("Would you like to upgrade any of your purchased elements?");
				System.out.println("Enter option (1-4): ");
				System.out.println("1. Display Balance.");
				System.out.println("2. Display My Elements and Upgrades.");
				System.out.println("3. Yes. Choose an element to update.");
				System.out.println("4. No. Don't update any elements.");

				menuOption = scanner.nextInt();

				switch (menuOption) {
				case 1:
					System.out.println("Current Balance is: $" + currentPlayer.getBalance());
					break;

				case 2:
					displayElementUpgrades();
					break;

				case 3:
					selectUpgradeElement();
					break;

				case 4:
					System.out.println("You have not updated any elements.\nReturning to the main menu...");
					displayMenu();
					break;

				default:
				}

			} while ((menuOption != 4));

		} catch (InputMismatchException e) {
			System.err.println("Please only enter a number into this menu");

			scanner.next();

			upgradeElement();
		}

	}

	/**
	 * Allows the player to select an element that they own, and are the owner of
	 * the System group for upgrade, providing the element has not already been
	 * fully upgraded.
	 */
	public void selectUpgradeElement() {

		// Yes - If element is not fully upgraded, upgrade
		int userElementOption;

		// Improve UI instructions here?
		int case3Counter = 1;

		for (Element element : currentPlayer.getElements()) {
			System.out.println(case3Counter + ". " + element + " (upgraded " + element.getNumUpgrades() + " times).");
			System.out.println("Cost to upgrade this element: $" + element.getUpgradePrice() + ".\n");
			case3Counter += 1;
		}
		System.out.println("Choose an element to upgrade from the list above. (Enter the number beside it):");

		userElementOption = scanner.nextInt();

		int indexToGet = userElementOption - 1;

		for (Element element : currentPlayer.getElements()) {

			// If user has correctly selected an element from the list
			if ((currentPlayer.getElements().indexOf(element) == indexToGet)) {

				// If Current Player Owns all Elements within a system
				if ((currentPlayer.isSystemOwner(element))) {

					// If not fully upgraded
					if (currentPlayer.getElements().get(indexToGet).canUpgrade()) {

						// If player has sufficient balance
						if (currentPlayer.getBalance() >= currentPlayer.getElements().get(indexToGet).getPrice()) {

							currentPlayer.completeTransaction(
									-currentPlayer.getElements().get(indexToGet).getUpgradePrice());
							currentPlayer.getElements().get(indexToGet)
									.setNumUpgrades(currentPlayer.getElements().get(indexToGet).getNumUpgrades() + 1);
							System.out.println(currentPlayer.getElements().get(indexToGet).getName()
									+ " has been upgraded by " + currentPlayer.getName() + "\n");

							if (currentPlayer.getElements().get(indexToGet).getNumUpgrades() == 3) {
								Element.fullyUpgradedElements.add(currentPlayer.getElements().get(indexToGet));
							}

						} else {
							System.out.println("Insufficient Balance.");
							System.out.println("Upgrading now would lead to Bankruptcy.\n");
						}

					} else {
						// Already fully upgraded
						System.out.println("This element has already been fully upgraded.\n");
					}

				} else {
					System.out.println(currentPlayer.getName() + " has not invested in all Elements of "
							+ element.getSystemGroup().getName() + " System.\n");
				}

			} else if (((currentPlayer.getElements().size() - 1) > indexToGet)
					|| ((currentPlayer.getElements().size() - 1) < indexToGet)) {
				// Improve UI instructions
				// User enters wrong number
				System.out.println("Not an option. Please enter a number from your element list above.\n");
			}

		}

	}

	/**
	 * Displays current Players Elements owned and Upgrades
	 */
	public void displayElementUpgrades() {
		int case2Counter = 1;
		for (Element element : currentPlayer.getElements()) {
			System.out.println(case2Counter + ". " + element + " (upgraded " + element.getNumUpgrades() + " times).");
			System.out.println("Cost to upgrade this element: $" + element.getUpgradePrice() + ".\n");
			case2Counter += 1;
		}

	}

	/**
	 * Will display the main turn menu. Allow players to check their Current
	 * Position, Current Balance, Owned Properties, Upgrades Give them an
	 * opportunity to Invest in in an undeveloped element Give them the opportunity
	 * to Upgrade and Owned Properties within a full owned System End their Turn or
	 * Quit Game
	 * 
	 * @throws InputMismatchException
	 */
	public void displayMenu() throws InputMismatchException {

		int option;

		try {

			do {
				System.out.println();
				System.out.print(currentPlayer.getName());
				System.out.println(", What would you like to do?");

				System.out.println("\n1. Check Current Status");
				System.out.println("2. Invest Element");
				System.out.println("3. Upgrade Element");
				System.out.println("4. Trade Element");
				System.out.println("5. End Turn");
				System.out.println("6. Quit Game");

				System.out.println("Enter option: ");
				option = scanner.nextInt();

				switch (option) {

				case 1:
					displayStatus();
					break;

				case 2:
					buyElement();
					break;

				case 3:
					upgradeElement();
					break;

				case 4:
					tradeElement();
					break;

				case 5:
					System.out.println("Ending your Turn!");
					endTurn();
					break;

				case 6:
					System.out.println(
							"Are you sure you want to quit the game?\nThis will end the game for all players.");
					isGameOver();
					quitGame();
					break;

				default:
					System.out.println("Invalid option. Please try again.");

				}

			} while (option != 6);

		} catch (InputMismatchException e) {
			// Needs fixed - currently ends game if exception occurs
			System.err.println("Please only enter a number into this menu");

			scanner.next();

			displayMenu();
		}

	}

	public void tradeElement() {

		// see all elements owned + owner
		// for loop that runs through all elements and if owned display elements + owner
		for (Player player : Players.players) {
			for (Element element : player.getElements()) {
				System.out.println(player.getName() + " has invested in: " + element.getName() + " Investment Cost: $"
						+ element.getPrice());
			}
		}

		// select a player to trade with
		int userTradeOption;
		int elementTradeOption;

		playerIndex = 1;
		elementIndex = 1;
		System.out.println("\nPlayers to Trade With:");
		for (Player player : Players.players) {

			if (player != currentPlayer) {

				System.out.println(playerIndex + ". " + player.getName());
			}

			playerIndex += 1;

		}
		try {
			
		
		System.out.println("Choose a Player from the list above to Trade with. (Enter the number beside it):");
		System.out.println("If you would like to return to the Main Menu enter 1.");

		userTradeOption = scanner.nextInt();

		indexToGet = userTradeOption - 1;
		System.out.println(
				currentPlayer.getName() + " has selected to trade with: " + Players.players.get(indexToGet).getName());

		// view trade partners elements
		for (Element element : Players.players.get(indexToGet).getElements()) {
			System.out.println(elementIndex + ". " + element.getName() + " $" + element.getPrice());
			elementIndex++;
		}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Please Choose a Valid Player option");
			displayMenu();
		}

		// Select and element to trade
		System.out.println("Choose an Element from the list above to Trade with. (Enter the number beside it):");
		System.out.println("If player does not have any Elemments to Trade, Enter 1 to return to the Main Menu. ");
try {
		elementTradeOption = scanner.nextInt();

		elementIndexToGet = elementTradeOption - 1;
		System.out.println("You have selected to trade:");
		for (Player player : Players.players) {

			if (player == Players.players.get(indexToGet)) {

				System.out.println(player.getElements().get(elementIndexToGet).getName());
			}
		}
}catch (IndexOutOfBoundsException e) {
	System.out.println("No Element To Trade");
	displayMenu();
}
		// offer Cash in exchange
		System.out.println(
				currentPlayer.getName() + " wishes to trade with " + Players.players.get(indexToGet).getName());
		System.out.println(currentPlayer.getName() + " is offering $"
				+ Players.players.get(indexToGet).getElements().get(elementIndexToGet).getPrice());

		acceptTrade();
	}

	/**
	 * Hands control to Trade Partner, to decide to accept or decline trade offer.
	 */
	public void acceptTrade() {
		// hand control to TradePartner
		holdCurrentPlayer = currentPlayer;

		currentPlayer = Players.players.get(indexToGet);
		System.out.println(currentPlayer.getName() + " is in control.");
		try {

			System.out.println("Would you like to accept this trade?");

			System.out.println("1. Yes, Accept Trade");
			System.out.println("2. No, Decline Trade");
			int tradeDecision;

			tradeDecision = scanner.nextInt();

			switch (tradeDecision) {

			// Trade partner:

			case 1:
				for (Player player : Players.players) {

					if (player == Players.players.get(indexToGet)) {

						// accepts:Remove Balance or element from currentPlayer
						holdCurrentPlayer.completeTransaction(-player.getElements().get(elementIndexToGet).getPrice());
						// adds Balance or element to TradePartner
						currentPlayer.completeTransaction(+player.getElements().get(elementIndexToGet).getPrice());
						// adds trade element to current Player
						holdCurrentPlayer.addElement(player.getElements().get(elementIndexToGet));

						// remove tradeElement from tradePartner
						currentPlayer.removeElement(player.getElements().get(elementIndexToGet));

						// return control to current player
						currentPlayer = holdCurrentPlayer;
						
					}
					System.out.println("\nTrade Completed!");
				}
				break;

			case 2:
				// declines: return control to current player to make new offer or return to
				// main menu.
				// return control to current player
				currentPlayer = holdCurrentPlayer;
				
				System.out.println();
				System.out.println(currentPlayer + " is in Control");
				declineTradeDecision();
				break;

			default:
				System.out.println("Please enter a valid menu option");
			}
		} catch (InputMismatchException e) {
			System.err.println("Please only enter a number into this menu");

			scanner.next();

			tradeElement();
		}
	}

	/**
	 * On a declined trade decision gives the Player option to make a new trade
	 * offer, or to return to the main turn menu.
	 */
	public void declineTradeDecision() {
		try {
			// return to trade menu
			System.out.println("Would you like to make another trade offer?");

			System.out.println("1. Yes, make another trade offer.");
			System.out.println("2. No, Return to Main Menu.");

			int declineTradeDecision;

			declineTradeDecision = scanner.nextInt();

			switch (declineTradeDecision) {
			case 1:
				tradeElement();
				break;

			case 2:
				displayMenu();
				break;

			default:
				System.out.println("Please enter a valid menu option");
			}
		} catch (InputMismatchException e) {
			System.err.println("Please only enter a number into this menu");

			scanner.next();

			declineTradeDecision();
		}

	}

	/**
	 * 
	 */
	public void payRent() {

		Square square = board.getSquare(currentPlayer.getPosition());
		int rentDecision;

		System.out.println("Rent to be paid is: $" + ((Element) square).getRent());

		// rent payment
		if (square instanceof Element && ((Element) square).isOwned()
				&& !((Element) square).getOwner().equals(currentPlayer)) {

			int rent = ((Element) square).getRent();

			Player owner = ((Element) square).getOwner();

			holdCurrentPlayer = currentPlayer;

			currentPlayer = owner;
			System.out.println(currentPlayer.getName() + " is in Control.");
			System.out.println(
					"\nWould you like to charge a Reasearch Funding Fee to " + holdCurrentPlayer.getName() + "?");

			System.out.println(holdCurrentPlayer.getName() + "'s current balance: $" + holdCurrentPlayer.getBalance());
			System.out.println("Research Funding Fee is: $" + rent);

			if (rent > holdCurrentPlayer.getBalance()) {
				System.out.println("This would cause " + holdCurrentPlayer.getName()
						+ " to enter a negative balance and risk bankruptcy!");
				System.out.println(
						"In the Spirit of Space Exploration waiving the Research Funding Fee \nmay enhance the chances of a successful Mission.");
			}

			try {
				System.out.println("Please select one of the following Options");

				System.out.println("1. Charge Research Funding Fee");
				System.out.println("2. Waive Research Funding Fee");

				rentDecision = scanner.nextInt();

				switch (rentDecision) {

				case 1:
					holdCurrentPlayer.completeTransaction(-rent);
					currentPlayer.completeTransaction(+rent);

					System.out
							.println(holdCurrentPlayer.getName() + " has paid Research Funding to " + owner.getName());
					break;

				case 2:
					System.out.println("Reasearch Funding Fee Waved!");
					break;
				default:
					System.out.println("Invalid Option Try Again");
				}

			} catch (InputMismatchException e) {
				System.err.println("Please only enter a number into this menu");

				scanner.next();
				// reset current player
				currentPlayer = holdCurrentPlayer;
				payRent();

			}
			currentPlayer = holdCurrentPlayer;

			System.out.println();
			System.out.println(currentPlayer.getName() + " is back in Control.");
		}
	}

	/**
	 * Menu Option 1 Display current players Balance, Position, Owned Properties etc
	 */
	public void displayStatus() {

		System.out.println(currentPlayer.getName() + "'s Current Status: ");
		System.out.println("Balance: " + currentPlayer.getBalance());
		System.out.println(
				"Square: " + currentPlayer.getPosition() + "\n" + board.getSquare(currentPlayer.getPosition()));
		System.out.println("Elements Owned: ");
		for (Element element : currentPlayer.getElements()) {
			System.out.println(element + " (upgraded " + element.getNumUpgrades() + " times).");
		}
		System.out.println("Total Elements Owned: " + currentPlayer.getElements().size());

	}

	/**
	 * Ends players turn. Swaps currentPlayer to next in the ArrayList Currently
	 * cycling through "Player1" twice after first go, before moving forward in the
	 * loop
	 */
	public void endTurn() {

		currentPlayer = Players.players.remove(0);

		Players.players.add(currentPlayer);
		currentPlayer = Players.players.get(0);

		isGameOver();

		takeTurn();

	}

	/**
	 * This methods gives the player the option to quit the game This will end the
	 * game for all players.
	 * 
	 * @throws InputMismatchException
	 * 
	 */
	public void quitGame() throws InputMismatchException {

		try {
			System.out.println("Please select an option. (1 or 2)");
			System.out.println("1. Yes. Quit.");
			System.out.println("2. No. Continue Game.");

			int quitOption = scanner.nextInt();
			switch (quitOption) {

			case 1:
				System.out.println("Quitting Game.");
				quitGame = true;
				gameOver();
				isGameOver();
				break;

			case 2:
				System.out.println("Returning to Game.");
				displayMenu();
				break;

			default:
				System.out.println("Invalid option. Please try again.");
			}

		} catch (InputMismatchException inputMismatchException) {
			System.err.println("Please only enter a number into this menu");

			scanner.next();

			quitGame();
		}

	}

	/**
	 * Checks if {@link Element#isAllElementsUpgraded} contains 10 elements and sets
	 * allElementsUpgraded to true
	 * 
	 * @return allElementsUpgraded
	 */
	public boolean isAllElementsUpgraded() {

		if (Element.fullyUpgradedElements.size() == 10) {
			allElementsUpgraded = true;
		} else {
			allElementsUpgraded = false;
		}

		return allElementsUpgraded;
	}

	/**
	 * Method is called at the end of Take Turn. Checks if player is bankrupt. If
	 * true, game over.
	 */
	public boolean isBankrupt() {
		if (currentPlayer.getBalance() < 0) {
			bankrupt = true;

		}
		return bankrupt;
	}

	/**
	 * Checks if game over scenarios have been met
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		if ((quitGame = true) || (bankrupt = true)) {
			gameOver = true;
		} else if (allElementsUpgraded = true) {
			gameOver = true;
		}

		return gameOver;
	}

	/**
	 * If gameOver is true. Method will decide Win or Lose scenario
	 */
	public void gameOver() {
		if ((quitGame = true) || (bankrupt = true)) {
			gameOver = true;
			displayLose();
			System.exit(0);
		} else if (allElementsUpgraded = true) {
			gameOver = true;
			displayWin();
			System.exit(0);
		} else {
			takeTurn();
		}
	}

	/**
	 * 
	 * Display final state of play of all players + their credits
	 */
	public void displayWin() {
		for (Player player : Players.players) {
			currentPlayer = player;
			displayStatus();
			System.out.println();
		}

		System.out.println();
		System.out.println("Congratulations!");
		System.out.println(
				"Your Team of Scientists have fully developed all of the Elements of the Artemis Space Program!");
		System.out.println("10");
		System.out.println("The Astronauts are Strapped in.");
		System.out.println("9");
		System.out.println("The Fuel Tanks are Full.");
		System.out.println("8");
		System.out.println("The Engines are Primed.");
		System.out.println("7");
		System.out.println("Life Support is Green");
		System.out.println("6");
		System.out.println("Mission Control is Online");
		System.out.println("5");
		System.out.println("The Stars are the Horizion");
		System.out.println("4");
		System.out.println("Get Ready to Fire");
		System.out.println("3");
		System.out.println("We wish you a good flight");
		System.out.println("2");
		System.out.println("Poyekhali!");
		System.out.println("1");
		System.out.println("BLAST OFF!!!");
	}

	/**
	 * Display final state of play if player quits, or bankrupt
	 */
	public void displayLose() {

		for (Player player : Players.players) {
			currentPlayer = player;
			displayStatus();
			System.out.println();
		}

		System.out.println("Unfortunately your team have been unable to fully develop the Artemis Space Program. "
				+ "\nThe next great adventure to the Moon will have to wait until another day. "
				+ "\nHuamnities mission to explore the Stars will continue!");

	}

}// end of class
