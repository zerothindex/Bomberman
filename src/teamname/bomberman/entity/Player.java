package teamname.bomberman.entity;

public class Player extends Entity {

	private final String name;

	private int speed = 5;

	public Player(String name) {
		this.name = name;
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
