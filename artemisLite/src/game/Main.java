/**
 * 
 */
package game;

/**
 * @author $Pat Moran 40044102
 *
 */
public class Main {

	/*
	 * @param args
	 */
	public static void main(String[] args) {
		ArtemisLite artemisLite = new ArtemisLite();

		System.out.println("Welcome to Artemis Lite!");
		System.out.println("A Space Exploration Development Simulation");

		System.out.println("\nThe Artmeis Program is an International human Spaceflight program "
				+ "\nwhich has the goal of landing the first woman and a person of colour at "
				+ "\nthe Lunar South Pole by 2024.");

		System.out.println("\nExperts from International Space Agencies are working in partnership "
				+ "\nto achieve the goals of Space Exploration, and eventually, colonisation.");

		System.out.println("\nIt is your job, a team of Scientist and Aerospace Engineers, "
				+ "\nto Invest In, and Develop the Systems that will be used to undertake "
				+ "\nthis historic adventure to Earth's closest neighbour.");
		
	

		artemisLite.startGame();

		System.out.println();
		artemisLite.setPlayerNumbers();
		artemisLite.setPlayerNames();
		artemisLite.decideStarter();

		do {
			artemisLite.takeTurn();
		} while (!artemisLite.isGameOver());

		artemisLite.gameOver();

		return;
	}

}