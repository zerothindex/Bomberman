package teamname.bomberman;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {

	private static Map<String, Image> imageMap;

	public static void loadImages() {
		imageMap = new HashMap<String, Image>();

		imageMap.put(Tile.GROUND.toString(), Toolkit.getDefaultToolkit().getImage("assets/ground.png"));
		imageMap.put(Tile.ROCK.toString(), Toolkit.getDefaultToolkit().getImage("assets/rock.png"));
		imageMap.put(Tile.WALL.toString(), Toolkit.getDefaultToolkit().getImage("assets/wall.png"));
		imageMap.put(Tile.BOMB.toString(), Toolkit.getDefaultToolkit().getImage("assets/bomb.png"));
		imageMap.put("explosion", Toolkit.getDefaultToolkit().getImage("assets/explosion.png"));
		imageMap.put("player", Toolkit.getDefaultToolkit().getImage("assets/player.png"));
	}

	public static Image get(String key) {
		return imageMap.get(key);
	}

	public static Image get(Tile type) {
		return get(type.toString());
	}

}
