package teamname.bomberman;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import teamname.bomberman.entity.Player;
import teamname.bomberman.tile.Ground;
import teamname.bomberman.tile.Rock;
import teamname.bomberman.tile.Wall;

public class ImageCache {

	private static Map<Class<?>, Image> imageMap;

	public static void loadImages() {
		imageMap = new HashMap<Class<?>, Image>();

		imageMap.put(Ground.class, Toolkit.getDefaultToolkit().getImage("assets/ground.png"));
		imageMap.put(Rock.class, Toolkit.getDefaultToolkit().getImage("assets/rock.png"));
		imageMap.put(Wall.class, Toolkit.getDefaultToolkit().getImage("assets/wall.png"));
		imageMap.put(Player.class, Toolkit.getDefaultToolkit().getImage("assets/player.png"));
	}

	public static Image get(Class<?> clazz) {
		return imageMap.get(clazz);
	}

}
