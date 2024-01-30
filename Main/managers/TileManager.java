package Main.managers;

import Main.helpers.LoadSave;
import Main.objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS, WATER, PATH,CASTLE,SPAWN;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }
 
    private void createTiles() {
		int id = 0;

        tiles.add(GRASS = new Tile(getSprite(8, 1),id++,"Grass"));
        tiles.add(WATER = new Tile(getSprite(0, 6),id++,"Water"));
        tiles.add(PATH = new Tile(getSprite(9, 0),id++,"Path"));
        tiles.add(CASTLE = new Tile(getSprite(6, 0),id++,"Castle"));
        tiles.add(SPAWN = new Tile(getSprite(9, 0), id++, "Spawn"));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }
	public Tile getTile(int id) {
		return tiles.get(id);
	}
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCoord,int yCoord) {
        return atlas.getSubimage(xCoord * 32, 32 * yCoord , 32, 32);
    }
}
