package teamname.bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

	public static final int KEY_CODE_NORTH = 38; // Up arrow
	public static final int KEY_CODE_SOUTH = 40; // Down arrow
	public static final int KEY_CODE_EAST = 39; //Right arrow
	public static final int KEY_CODE_WEST = 37; // Left arrow
	public static final int KEY_CODE_ACTION = 32; // Space

	private static boolean northPressed;
	private static boolean southPressed;
	private static boolean eastPressed;
	private static boolean westPressed;
	private static boolean actionPressed;

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

	public static boolean isActionPressed() {
		return actionPressed;
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
		case KEY_CODE_ACTION:
			actionPressed = true;
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
		case KEY_CODE_ACTION:
			actionPressed = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
