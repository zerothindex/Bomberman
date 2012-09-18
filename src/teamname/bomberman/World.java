package teamname.bomberman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class World {

	public enum Direction {
		NORTH, SOUTH, EAST, WEST, NONE;
	}

	private final int width;
	private final int height;

	public final static int GRID_WIDTH = 64;
	public final static double ROCK_CHANCE = 0.2;
	public final static long FUSE_TIME = 3000;
	public final static long EXPLOSION_TIME = 2000;
	public final static int EXPLOSION_RADIUS = 3;

	// Tile[y][x]
	private final Tile[][] tiles;
	private final Player[] players;
	private final ArrayList<Explosion> explosions;
	private final Timer timer;

	public World(int width, int height, Player[] players) {
		this.width = width;
		this.height = height;
		this.players = players;
		tiles = new Tile[height][width];
		explosions = new ArrayList<Explosion>();
		timer = new Timer();
		initWorld();
	}

	private void initWorld() {
		// Create the tiles.
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				if (r == 0 || c == 0 || r == height-1 || c == width-1) {
					// Create border walls.
					tiles[r][c] = Tile.WALL;
				} else if (r % 2 == 0 && c % 2 == 0) {
					// Create grid walls.
					tiles[r][c] = Tile.WALL;
				} else if (Math.random() < ROCK_CHANCE) {
					// Create destructable rocks.
					tiles[r][c] = Tile.ROCK;
				} else {
					// Lastly, create ground.
					tiles[r][c] = Tile.GROUND;
				}
			}
		}

		// Place players.
		for (int i = 0; i < players.length; i++) {
			players[i].setX((int) (1.5 * GRID_WIDTH));
			players[i].setY((int) (1.5 * GRID_WIDTH));
		}
	}

	public boolean move(Player player, Direction dir) {
		Direction actualDir = getActualDirection(player, dir);

		if (actualDir == Direction.NONE) {
			if (dir == Direction.NORTH || dir == Direction.SOUTH) {
				centerY(player);
			} else if (dir == Direction.EAST || dir == Direction.WEST) {
				centerX(player);
			}
			return false;
		}

		// Move the player and make sure it is aligned in the middle of the tile.
		switch (actualDir) {
		case NORTH:
			player.setY(player.getY() - player.getSpeed());
			if (dir == actualDir) centerX(player);
			else if (getOffset(player.getY()) < 0) centerY(player);
			break;
		case SOUTH:
			player.setY(player.getY() + player.getSpeed());
			if (dir == actualDir) centerX(player);
			else if (getOffset(player.getY()) > 0) centerY(player);
			break;
		case EAST:
			player.setX(player.getX() + player.getSpeed());
			if (dir == actualDir) centerY(player);
			else if (getOffset(player.getX()) > 0) centerX(player);
			break;
		case WEST:
			player.setX(player.getX() - player.getSpeed());
			if (dir == actualDir) centerY(player);
			else if (getOffset(player.getX()) < 0) centerX(player);
			break;
		}
		return true;
	}

	private Direction getActualDirection(Player player, Direction dir) {
		if (dir == Direction.NONE) return Direction.NONE;
		Tile dest = null;
		boolean centered = false;
		switch (dir) {
		case NORTH:
			centered = getOffset(player.getX()) == 0;
			if (centered && getOffset(player.getY()) > 0) return dir;
			dest = tiles[getGrid(player.getY()) - 1][getGrid(player.getX())];
			break;
		case SOUTH:
			centered = getOffset(player.getX()) == 0;
			if (centered && getOffset(player.getY()) < 0) return dir;
			dest = tiles[getGrid(player.getY()) + 1][getGrid(player.getX())];
			break;
		case EAST:
			centered = getOffset(player.getY()) == 0;
			if (centered && getOffset(player.getX()) < 0) return dir;
			dest = tiles[getGrid(player.getY())][getGrid(player.getX()) + 1];
			break;
		case WEST:
			centered = getOffset(player.getY()) == 0;
			if (centered && getOffset(player.getX()) > 0) return dir;
			dest = tiles[getGrid(player.getY())][getGrid(player.getX()) - 1];
			break;
		}
		if (centered && !dest.isSolid()) return dir;
		// Player can't move in attempted direction. If the player isn't dead center
		// in a tile, nudge them the right direction.
		int tileOffset; // how far from the center of the tile the player is in dir.
		if (dir == Direction.NORTH || dir == Direction.SOUTH) {
			tileOffset = getOffset(player.getX());
		} else {
			tileOffset = getOffset(player.getY());
		}

		if (!dest.isSolid()) {
			// Head towards center of tile before in desired direction.
			if (dir == Direction.NORTH || dir == Direction.SOUTH) {
				if (tileOffset > 0) {
					return Direction.WEST;
				} else if (tileOffset < 0) {
					return Direction.EAST;
				}
			} else {
				if (tileOffset > 0) {
					return Direction.NORTH;
				} else if (tileOffset < 0) {
					return Direction.SOUTH;
				}
			}
		}
		return Direction.NONE;
	}

	public void dropBomb(Player player) {
		final int r = getGrid(player.getY());
		final int c = getGrid(player.getX());
		tiles[r][c] = Tile.BOMB;
		final Explosion explosion = new Explosion(this, r, c, EXPLOSION_RADIUS);
		// Detonation timer.
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				explosions.add(explosion);
			}
		}, FUSE_TIME);
		// Extinguishing timer.
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (int[] coord : explosion.getTiles()) {
					tiles[coord[0]][coord[1]] = Tile.GROUND;
				}
				explosions.remove(explosion);
			}
		}, FUSE_TIME + EXPLOSION_TIME);
	}

	public int getGrid(int coord) {
		return coord / GRID_WIDTH;
	}

	/**
	 * @param coord
	 * @return Offset of a global coordinate from the CENTER of the tile it falls in.
	 */
	public int getOffset(int coord) {
		return coord % GRID_WIDTH - GRID_WIDTH / 2;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public Player[] getPlayers() {
		return players;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Explosion> getExplosions() {
		return (ArrayList<Explosion>) explosions.clone();
	}

	public Tile getRelative(int r, int c, Direction dir) {
		switch (dir) {
		case NORTH:
			if (r == 0) return null;
			return tiles[r - 1][c];
		case SOUTH:
			if (r == height - 1) return null;
			return tiles[r + 1][c];
		case EAST:
			if (c == width - 1) return null;
			return tiles[r][c + 1];
		case WEST:
			if (c == 0) return null;
			return tiles[r][c - 1];
		}
		return null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Centers the Entity so that it lies in the center of the tile on the X axis.
	 */
	private void centerX(Player entity) {
		entity.setX(entity.getX() - getOffset(entity.getX()));
	}

	/**
	 * Centers the Entity so that it lies in the center of the tile on the Y axis.
	 */
	private void centerY(Player entity) {
		entity.setY(entity.getY() - getOffset(entity.getY()));
	}
}
