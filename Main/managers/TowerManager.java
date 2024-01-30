package Main.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.scenes.Playing;
import Main.Monster.AMonster;
import Main.Tower.*;
import Main.helpers.LoadSave;

/**
 * Manages towers in the game, including their images, drawing, and updates.
 */
public class TowerManager {

    private Playing playing; // get the board
    private BufferedImage[] towerImgs; // get the tower images
    // private FireTower towerTest; // to test the functionality
    private MonsterManager monsterManager;
    private AMonster[] monsterList;
    public ArrayList<ATower> towers = new ArrayList<>();
    public ATower normalTower, fireTower, iceTower, longRangeTower, zoneTower;
    public BufferedImage atlas;

    /**
     * Constructs a TowerManager with the specified playing scene.
     *
     * @param playing The playing scene where the tower manager operates.
     */
    public TowerManager(Playing playing, MonsterManager monsterManager) {
        loadAtlas();
        this.playing = playing;
        this.monsterManager = monsterManager;
        this.towerImgs = new BufferedImage[5];
        loadTowerImgs();
    }

    /**
     * Loads tower images from the sprite atlas.
     */
    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs[0] = atlas.getSubimage(0 * 32, 2 * 32, 32, 32);
        towerImgs[1] = atlas.getSubimage(1 * 32, 2 * 32, 32, 32);
        towerImgs[2] = atlas.getSubimage(2 * 32, 2 * 32, 32, 32);
        towerImgs[3] = atlas.getSubimage(3 * 32, 2 * 32, 32, 32);
        towerImgs[4] = atlas.getSubimage(4 * 32, 2 * 32, 32, 32);
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public ATower getTile(int id) {
        return towers.get(id);
    }

    /**
     * Draws the specified tower on the graphics context.
     *
     * @param e The tower to be drawn.
     * @param g The graphics context.
     */
    public void draw(Graphics g) {

        for (ATower t : towers) {
            // System.out.println(t.getTowerType());
            if (t instanceof NormalTower)
                g.drawImage(towerImgs[0], t.getTowerPosition().getX(), t.getTowerPosition().getY(), null);
            if (t instanceof FireTower)
                g.drawImage(towerImgs[1], t.getTowerPosition().getX(), t.getTowerPosition().getY(), null);
            if (t instanceof IceTower)
                g.drawImage(towerImgs[2], t.getTowerPosition().getX(), t.getTowerPosition().getY(), null);
            if (t instanceof LongRangeTower)
                g.drawImage(towerImgs[3], t.getTowerPosition().getX(), t.getTowerPosition().getY(), null);
            if (t instanceof ZoneTower)
                g.drawImage(towerImgs[4], t.getTowerPosition().getX(), t.getTowerPosition().getY(), null);
        }

    }

    /**
     * Updates the tower manager.
     */
    public void update() {
        // Update logic goes here if needed
        monsterList = new AMonster[MonsterManager.getMonsterCopyList().size()];
        int i = 0;
        for (AMonster monster : MonsterManager.getMonsterCopyList()) {
            monsterList[i] = monster;
            i++;
        }
        for (ATower t : towers) {
            t.attack(monsterList);
        }
    }

    /**
     * Returns the array of tower images.
     *
     * @return The tower images array.
     */
    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    /**
     * Gets the playing scene associated with the tower manager.
     *
     * @return The playing scene.
     */
    public Playing getPlaying() {
        return playing;
    }

    public void addTower(ATower selectedTower, int xPos, int yPos) {
        if (selectedTower == null)
            return;
        if (selectedTower instanceof NormalTower)
            towers.add(new NormalTower(xPos, yPos));
        else if (selectedTower instanceof FireTower)
            towers.add(new FireTower(xPos, yPos));
        else if (selectedTower instanceof IceTower)
            towers.add(new IceTower(xPos, yPos));
        else if (selectedTower instanceof LongRangeTower)
            towers.add(new LongRangeTower(xPos, yPos));
        else if (selectedTower instanceof ZoneTower)
            towers.add(new ZoneTower(xPos, yPos));
    }

    public BufferedImage getSprite(int id) {
        return towerImgs[id];
    }

}