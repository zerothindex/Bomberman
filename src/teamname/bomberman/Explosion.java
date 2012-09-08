package teamname.bomberman;

import java.util.ArrayList;


public class Explosion {

	private final ArrayList<int[]> tiles;
	private final World world;

	public Explosion(World world, int r, int c, int radius) {
		this.world = world;
		tiles = new ArrayList<int[]>();
		tiles.add(new int[] { r, c});
		explodeDirection(r-1, c, -1, 0, radius-1); // North
		explodeDirection(r+1, c, 1, 0, radius-1); // South
		explodeDirection(r, c+1, 0, 1, radius-1); // East
		explodeDirection(r, c-1, 0, -1, radius-1); // West
	}

	private void explodeDirection(int r, int c, int dr, int dc, int radius) {
		if (radius <= 0) return;
		if (world.getTiles()[r][c] == Tile.GROUND || world.getTiles()[r][c].isDestructable()) {
			tiles.add(new int[] { r, c});
			if (world.getTiles()[r][c].isDestructable()) return;
		} else {
			return;
		}
		if ( r + dr >= 0 && r + dr < world.getHeight()
				&& c + dc >= 0 && c + dc < world.getWidth()) {
			explodeDirection(r + dr, c + dc, dr, dc, radius - 1);
		}
	}

	/**
	 * @return Coordinates effected by the explosion. Each int[] is a (r,c) coord.
	 */
	public ArrayList<int[]> getTiles() {
		return tiles;
	}
}
