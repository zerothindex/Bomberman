package teamname.bomberman;

import javax.swing.JFrame;


/**
 * Launches the UI and starts game logic.
 *
 */
public class Main  {

	private Game game;
	private GamePainter gamePainter;

	private static final int ROWS = 15;
	private static final int COLS = 11;

	public static void main(String[] args) {
		Main main = new Main(new Game(ROWS, COLS, new Player[] { new Player("player1") }));
	}

	public Main(Game game) {
		this.game = game;
		gamePainter = new GamePainter(game);
		game.setPainter(gamePainter);

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, World.GRID_WIDTH*ROWS, (World.GRID_WIDTH*COLS));
		window.setResizable(false);
		window.addKeyListener(new PlayerController());
		window.getContentPane().add(gamePainter);
		window.setVisible(true);

		game.setRunning(true);
	}
}
