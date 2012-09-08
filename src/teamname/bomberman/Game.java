package teamname.bomberman;


/**
 * Runs a Bomberman game.
 *
 */
public class Game extends Thread {

	private final long SLEEP_TIME = 20;

	private boolean gameRunning;

	private final World world;

	private GamePainter painter;

	private int measuredFps;

	public Game(int worldWidth, int worldHeight, Player[] players) {
		world = new World(worldWidth, worldHeight, players);
	}

	public void setPainter(GamePainter painter) {
		this.painter = painter;
	}

	/**
	 * Start or stop the game from running.
	 */
	public void setRunning(boolean running) {
		gameRunning = running;
		if (gameRunning && !isAlive()) start();
	}

	/**
	 * Maintains the update/render cycle.
	 */
	@Override
	public void run() {
		long start;
		while (gameRunning) {
			start = System.currentTimeMillis();
			update();
			render();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
			measuredFps = (int) (1000/(System.currentTimeMillis() - start));
		}
	}

	/**
	 * Take input and update the game state.
	 */
	private void update() {
		// If the player could move in the attempted direction, take no more move input.
		// If the player couldn't move in one direction, check the other keys.
		if (PlayerController.isNorthPressed()) {
			if (world.move(world.getPlayers()[0], World.Direction.NORTH)) return;
		} else if (PlayerController.isSouthPressed()) {
			if (world.move(world.getPlayers()[0], World.Direction.SOUTH)) return;
		}
		if (PlayerController.isEastPressed()) {
			if (world.move(world.getPlayers()[0], World.Direction.EAST)) return;
		} else if (PlayerController.isWestPressed()) {
			if (world.move(world.getPlayers()[0], World.Direction.WEST)) return;
		}

		if (PlayerController.isActionPressed()) {
			world.dropBomb(world.getPlayers()[0]);
		}
	}

	/**
	 * Draw the game state.
	 */
	private void render() {
		painter.repaint();
	}

	public World getWorld() {
		return world;
	}

	public int getFps() {
		return measuredFps;
	}
}
