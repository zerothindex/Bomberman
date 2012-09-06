package teamname.bomberman;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import teamname.bomberman.entity.Player;
import teamname.bomberman.tile.Tile;

public class GamePainter extends JComponent {

	private static final long serialVersionUID = 1L;

	private Game game;

	public GamePainter(Game game) {
		ImageCache.loadImages();
		this.game = game;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		// Draw tiles.
		Tile[][] tiles = game.getWorld().getTiles();
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[0].length; c++) {
				graphics.drawImage(ImageCache.get(tiles[r][c].getClass()),
						c*World.GRID_WIDTH, r*World.GRID_WIDTH, null);
			}
		}
		// Draw players.
		Player[] players = game.getWorld().getPlayers();
		for (int i = 0; i < players.length; i++) {

		}

		graphics.finalize();
	}
}
