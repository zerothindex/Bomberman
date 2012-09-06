package teamname.bomberman.tile;

public class Rock extends Tile {

	private final int row;
	private final int col;

	public Rock(int r, int c) {
		row = r;
		col = c;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean isDestructable() {
		return true;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getCol() {
		return col;
	}

}
