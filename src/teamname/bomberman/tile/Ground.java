package teamname.bomberman.tile;

public class Ground extends Tile {

	private final int row;
	private final int col;

	public Ground(int r, int c) {
		row = r;
		col = c;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean isDestructable() {
		return false;
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
