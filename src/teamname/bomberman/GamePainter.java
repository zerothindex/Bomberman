package teamname.bomberman;

import java.awt.Color;
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
				//graphics.drawString("("+r+","+c+")", c*World.GRID_WIDTH+20, r*World.GRID_WIDTH+40);
			}
		}
		// Draw players.
		Player[] players = game.getWorld().getPlayers();
		for (int i = 0; i < players.length; i++) {
			graphics.drawImage(ImageCache.get(players[i].getClass()),
					players[i].getX() - World.GRID_WIDTH/2,
					players[i].getY() - World.GRID_WIDTH/2, null);
		}

		// Useful info.
		Player p1 = game.getWorld().getPlayers()[0];
		graphics.setColor(Color.WHITE);
		graphics.drawString("fps:  "+game.getFps(), 20, 20);
		graphics.drawString("p1:   "+p1.getX() + ", " + p1.getY(), 20, 40);
		graphics.drawString("grid: "+game.getWorld().getGrid(p1.getY())+", "
				+ game.getWorld().getGrid(p1.getX()), 20, 60);

		graphics.finalize();
	}
}
