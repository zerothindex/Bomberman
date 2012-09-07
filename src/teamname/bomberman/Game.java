package teamname.bomberman;

import teamname.bomberman.entity.Player;

/**
 * Runs a Bomberman game.
 *
 */
public class Game extends Thread {

	private final long SLEEP_TIME = 30;

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
		if (PlayerController.isNorthPressed()) {
			world.move(world.getPlayers()[0], World.Direction.NORTH);
		} else if (PlayerController.isSouthPressed()) {
			world.move(world.getPlayers()[0], World.Direction.SOUTH);
		} else if (PlayerController.isEastPressed()) {
			world.move(world.getPlayers()[0], World.Direction.EAST);
		} else if (PlayerController.isWestPressed()) {
			world.move(world.getPlayers()[0], World.Direction.WEST);
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
