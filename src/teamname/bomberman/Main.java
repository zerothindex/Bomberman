package teamname.bomberman;

/**
 * Launches the game.
 * 
 * @author Zachary Potter (zsp9362@rit.edu)
 * @author Channon Price (chp9488@rit.edu)
 *
 */
public class Main {
	
	private final long SLEEP_TIME = 50;
	
	private boolean gameRunning;
	
	/**
	 * Maintains the update/render cycle.
	 */
	private void runLoop() {
		while (gameRunning) {
			update();
			render();
			
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	/**
	 * Load graphics, set up game, etc.
	 */
	private void init() {
		
	}
	
	/**
	 * Take input and update the game state.
	 */
	private void update() {
		
	}
	
	/**
	 * Draw the game state.
	 */
	private void render() {
		
	}
}
