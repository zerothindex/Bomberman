package teamname.bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import teamname.bomberman.entity.Player;

/**
 * Launches the UI and starts game logic.
 *
 */
public class Main implements KeyListener {

	private Game game;
	private GamePainter gamePainter;

	private static Player player = new Player("player1");

	public final int KEY_CODE_NORTH = 38;
	public final int KEY_CODE_SOUTH = 40;
	public final int KEY_CODE_EAST = 39;
	public final int KEY_CODE_WEST = 37;

	private static final int ROWS = 15;
	private static final int COLS = 11;

	public static void main(String[] args) {
		Main main = new Main(new Game(ROWS, COLS, new Player[] { player }));
	}

	public Main(Game game) {
		this.game = game;
		gamePainter = new GamePainter(game);
		game.setPainter(gamePainter);

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, World.GRID_WIDTH*ROWS, (World.GRID_WIDTH*COLS));
		window.setResizable(false);
		window.addKeyListener(this);
		window.getContentPane().add(gamePainter);
		window.setVisible(true);

		game.setRunning(true);
	}

	private long lastPress = 0;

	@Override
	public void keyPressed(KeyEvent event) {
		if (lastPress != 0) return;
		World.Direction dir = World.Direction.NONE;
		switch (event.getKeyCode()) {
		case KEY_CODE_NORTH:
			dir = World.Direction.NORTH;
			break;
		case KEY_CODE_SOUTH:
			dir = World.Direction.SOUTH;
			break;
		case KEY_CODE_EAST:
			dir = World.Direction.EAST;
			break;
		case KEY_CODE_WEST:
			dir = World.Direction.WEST;
			break;
		default:
			return;
		}
		lastPress = System.currentTimeMillis();
		System.out.println("move "+ dir.toString());
		game.getWorld().move(player, dir);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (System.currentTimeMillis() - lastPress > 40) {
			lastPress = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
