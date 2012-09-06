package teamname.bomberman.entity;

public class Player extends Entity {

	private final String name;

	private int speed = 5;

	private int x = -1;
	private int y = -1;

	public Player(String name) {
		this.name = name;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return how many pixels this Player moves each frame.
	 */
	public int getSpeed() {
		return speed;
	}

}
