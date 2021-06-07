package game;

/**
 * 
 * @author $Pat Moran 40044102
 *
 */
public class Board {

	static final int NUM_SQUARES = 12;

	private Square[] squares = new Square[NUM_SQUARES];

	/*
	 * Creating the system 4 system groups
	 */
	private SystemGroup astronaughtProgram = new SystemGroup("Astronaught Training Program");
	private SystemGroup slsBoosters = new SystemGroup("SLS Boosters");
	private SystemGroup spaceship = new SystemGroup("Spaceship");
	private SystemGroup landingShuttle = new SystemGroup("Landing Shuttle");

	Board(Dice dice) {
		squares[0] = new Square("Go");
		squares[1] = new Element("[Astronaught Training Program] Zero Gravity", 50, 50, 0, false, null, 50,
				astronaughtProgram);
		squares[2] = new Element("[Astronaught Training Program] Flight Training", 50, 50, 0, false, null, 50,
				astronaughtProgram);
		squares[3] = new Element("[SLS Boosters] Stage1", 75, 75, 0, false, null, 75, slsBoosters);
		squares[4] = new Element("[SLS Boosters] Stage2", 75, 75, 0, false, null, 75, slsBoosters);
		squares[5] = new Element("[SLS Boosters] Stage3", 75, 75, 0, false, null, 75, slsBoosters);
		squares[6] = new Square("Free Space");
		squares[7] = new Element("[Spaceship] Control Module", 75, 75, 0, false, null, 75, spaceship);
		squares[8] = new Element("[Spaceship] Life Support", 75, 75, 0, false, null, 75, spaceship);
		squares[9] = new Element("[Spaceship] Fuselage", 75, 75, 0, false, null, 75, spaceship);
		squares[10] = new Element("[Landing Shuttle] Lunar Module", 100, 100, 0, false, null, 100, landingShuttle);
		squares[11] = new Element("[Landing Shuttle] Retroboosters", 100, 100, 0, false, null, 100, landingShuttle);

	}

	public Square getSquare(int index) {
		return squares[index];
	}

	public Element getElement(int index) {
		return (Element) squares[index];
		
	}
	
	

}
