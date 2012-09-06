package teamname.bomberman.tile;

public abstract class Tile {

	/**
	 * @return true iff a player or explosion can pass through this tile.
	 */
	public abstract boolean isSolid();

	/**
	 * @return true iff an explosion will effect this tile.
	 */
	public abstract boolean isDestructable();

	public abstract int getRow();
	public abstract int getCol();

}
