package teamname.bomberman;

import teamname.bomberman.entity.Player;
import teamname.bomberman.tile.Ground;
import teamname.bomberman.tile.Rock;
import teamname.bomberman.tile.Tile;
import teamname.bomberman.tile.Wall;

public class World {

	public enum Direction {
		NORTH, SOUTH, EAST, WEST, NONE;
	}

	private final int width;
	private final int height;

	public final static int GRID_WIDTH = 64;

	// Tile[y][x]
	private final Tile[][] tiles;
	private final Player[] players;

	public World(int width, int height, Player[] players) {
		this.width = width;
		this.height = height;
		this.players = players;
		tiles = new Tile[height][width];

		initWorld();
	}

	private void initWorld() {
		// Create the tiles.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (y == 0 || x == 0 || y == height-1 || x == width-1) {
					// Create border walls.
					tiles[y][x] = new Wall(x, y);
				} else if (y % 2 == 0 && x % 2 == 0) {
					// Create grid walls.
					tiles[y][x] = new Wall(x, y);
				} else if (Math.random() < 0.2) {
					// Create destructable rocks.
					tiles[y][x] = new Rock(x, y);
				} else {
					// Lastly, create ground.
					tiles[y][x] = new Ground(x, y);
				}
			}
		}

		// Place players.
		for (int i = 0; i < players.length; i++) {
			players[i].setX(1);
			players[i].setY(1);
		}
	}

	public boolean move(Player player, Direction dir) {
		Direction actualDir = getActualDirection(player, dir);
		switch (actualDir) {
		case NORTH:
			player.setY(player.getY() + player.getSpeed());
			break;
		case SOUTH:
			player.setY(player.getY() - player.getSpeed());
			break;
		case EAST:
			player.setX(player.getX() + player.getSpeed());
			break;
		case WEST:
			player.setX(player.getX() - player.getSpeed());
			break;
		case NONE:
			return false;
		}
		return true;
	}

	public Direction getActualDirection(Player player, Direction dir) {
		if (dir == Direction.NONE) return Direction.NONE;
		Tile dest = null;
		switch (dir) {
		case NORTH:
			dest = tiles[getGrid(player.getY()) - 1][getGrid(player.getX())];
			break;
		case SOUTH:
			dest = tiles[getGrid(player.getY()) + 1][getGrid(player.getX())];
			break;
		case EAST:
			dest = tiles[getGrid(player.getY())][getGrid(player.getX()) + 1];
			break;
		case WEST:
			dest = tiles[getGrid(player.getY())][getGrid(player.getX()) - 1];
			break;
		}
		if (!dest.isSolid()) return dir;

		// Player can't move in attempted direction. If the player isn't dead center
		// in a tile, nudge them the right direction.
		int tileOffset; // how far from the center of the tile the player is in dir.
		if (dir == Direction.NORTH || dir == Direction.SOUTH) {
			tileOffset = (int) (player.getX() % GRID_WIDTH - 0.5 * GRID_WIDTH);
		} else {
			tileOffset = (int) (player.getY() % GRID_WIDTH - 0.5 * GRID_WIDTH);
		}

		if (dir == Direction.NORTH || dir == Direction.SOUTH) {
			if (tileOffset > GRID_WIDTH / 3) {
				return Direction.EAST;
			} else if (tileOffset < GRID_WIDTH / -3) {
				return Direction.WEST;
			}
		} else {
			if (tileOffset > GRID_WIDTH / 3) {
				return Direction.NORTH;
			} else if (tileOffset < GRID_WIDTH / -3) {
				return Direction.SOUTH;
			}
		}
		return Direction.NONE;
	}

	public int getGrid(int coord) {
		return coord % GRID_WIDTH;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public Player[] getPlayers() {
		return players;
	}

}
