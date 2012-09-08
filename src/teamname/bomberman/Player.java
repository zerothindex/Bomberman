package teamname.bomberman;

public class Player {

	private int speed = 5;

	private final String name;

	private int x = -1;
	private int y = -1;

	public Player(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return how many pixels this Player moves each frame.
	 */
	public int getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}

}
