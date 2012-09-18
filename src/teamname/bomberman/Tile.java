package teamname.bomberman;

public enum Tile {

	WALL (true, false),
	ROCK (true, true),
	GROUND (false, false),
	BOMB (true, false);

	private final boolean solid;
	private final boolean destructable;

	Tile (boolean isSolid, boolean isDestructable) {
		solid = isSolid;
		destructable = isDestructable;
	}

	/**
	 * @return true iff a player or explosion can pass through this tile.
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @return true iff an explosion will effect this tile.
	 */
	public boolean isDestructable() {
		return destructable;
	}

}
