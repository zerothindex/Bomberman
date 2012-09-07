package teamname.bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

	public static final int KEY_CODE_NORTH = 38;
	public static final int KEY_CODE_SOUTH = 40;
	public static final int KEY_CODE_EAST = 39;
	public static final int KEY_CODE_WEST = 37;

	private static boolean northPressed;
	private static boolean southPressed;
	private static boolean eastPressed;
	private static boolean westPressed;

	public static boolean isNorthPressed() {
		return northPressed;
	}

	public static boolean isSouthPressed() {
		return southPressed;
	}

	public static boolean isEastPressed() {
		return eastPressed;
	}

	public static boolean isWestPressed() {
		return westPressed;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KEY_CODE_NORTH:
			northPressed = true;
			break;
		case KEY_CODE_SOUTH:
			southPressed = true;
			break;
		case KEY_CODE_EAST:
			eastPressed = true;
			break;
		case KEY_CODE_WEST:
			westPressed = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KEY_CODE_NORTH:
			northPressed = false;
			break;
		case KEY_CODE_SOUTH:
			southPressed = false;
			break;
		case KEY_CODE_EAST:
			eastPressed = false;
			break;
		case KEY_CODE_WEST:
			westPressed = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
