package Main.scenes;

import Main.Game;
import Main.Castle.Castle;
import Main.helpers.LoadSave;
import Main.managers.MonsterManager;
import Main.managers.TowerManager;
import Main.ui.ActionBar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Main.Player.*;
import Main.Tower.*;

import static Main.GameStates.GAMEOVER;
import static Main.GameStates.setGameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the playing scene in the game. This scene manages the gameplay,
 * including rendering the game elements, handling player input, and updating
 * the game state.
 */
public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private ActionBar bottomBar;
    private MonsterManager monsterManager;
    private TowerManager towerManager;
    private int reset = 0;
    private Castle castle;
    private int mouseX = 7, mouseY = 8;
    private ATower selectedTower = null;
    private Player player;
    private BufferedImage[] towerImgs; // get the tower images

    // Getter
    public int getReset() {
        return this.reset;
    }

    public MonsterManager getMonsterManager() {
        return this.monsterManager;
    }

    public Playing(Game game, Player player, Castle castle) {
        super(game);
        loadDefaultLevel();
        bottomBar = new ActionBar(0, 640, 640, 100, this);
        monsterManager = new MonsterManager(this, player, castle);
        towerManager = new TowerManager(this, monsterManager);
        this.castle = castle;
        this.player = player;
        this.towerImgs = new BufferedImage[5];

        loadTowerImgs();

        playSoundtrack();

    }

    private void playSoundtrack() {
        try {
            // Load the audio file (replace "path/to/your/soundtrack.wav" with the actual
            // file path)
            File audioFile = new File("res/Glorious_morning.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a Clip object to play the audio
            Clip clip = AudioSystem.getClip();

            // Open the audio stream and start playing
            clip.open(audioStream);
            clip.start();

            // Loop the soundtrack (optional)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
    }

    /**
     * Sets the level data for the playing scene.
     *
     * @param lvl The 2D array representing the level data.
     */
    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void setSelectedTower(ATower tower) {
        this.selectedTower = tower;
    }

    /**
     * Updates the game logic, including monster and tower management.
     */
    public void update() {
        monsterManager.update();
        towerManager.update();
        if (bottomBar.getReset() == 1) {
            this.reset = 1;
        }

        if (castle.getHp() == 0) {
            setGameState(GAMEOVER);
        }
    }

    /**
     * Renders the playing scene, including the level, bottom bar, monsters, and
     * towers.
     *
     * @param g The graphics context on which to render.
     */
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        bottomBar.draw(g);
        monsterManager.draw(g);
        towerManager.draw(g);
        drawSelectedTower(g);
    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs[0] = atlas.getSubimage(0 * 32, 2 * 32, 32, 32);
        towerImgs[1] = atlas.getSubimage(1 * 32, 2 * 32, 32, 32);
        towerImgs[2] = atlas.getSubimage(2 * 32, 2 * 32, 32, 32);
        towerImgs[3] = atlas.getSubimage(3 * 32, 2 * 32, 32, 32);
        towerImgs[4] = atlas.getSubimage(4 * 32, 2 * 32, 32, 32);

    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null) {
            // g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()],mouseX, mouseY, null);
            if (selectedTower instanceof NormalTower)
                g.drawImage(towerImgs[0], mouseX, mouseY, null);
            else if (selectedTower instanceof FireTower)
                g.drawImage(towerImgs[1], mouseX, mouseY, null);
            else if (selectedTower instanceof IceTower)
                g.drawImage(towerImgs[2], mouseX, mouseY, null);
            else if (selectedTower instanceof LongRangeTower)
                g.drawImage(towerImgs[3], mouseX, mouseY, null);
            else if (selectedTower instanceof ZoneTower)
                g.drawImage(towerImgs[4], mouseX, mouseY, null);
        }

    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }

    /**
     * Handles mouse click events for the playing scene.
     *
     * @param x The x-coordinate of the mouse click.
     * @param y The y-coordinate of the mouse click.
     */
    public void mouseClicked(int x, int y) {
        if (y >= 640)
            bottomBar.mouseClicked(x, y);
        else if (bottomBar.getSelectedTower() != null) {
            if (selectedTower != null && player.canBuy(selectedTower)) {
                towerManager.addTower(selectedTower, mouseX, mouseY);
                player.setGold(-selectedTower.getPrice());
                selectedTower = null;
            }
        }
    }

    /**
     * Handles mouse move events for the playing scene.
     *
     * @param x The x-coordinate of the mouse.
     * @param y The y-coordinate of the mouse.
     */
    public void mouseMoved(int x, int y) {
        if (y >= 640)
            bottomBar.mouseMoved(x, y);
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    /**
     * Handles mouse press events for the playing scene.
     *
     * @param x The x-coordinate of the mouse press.
     * @param y The y-coordinate of the mouse press.
     */
    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x, y);
        } else {

        }
    }

    /**
     * Handles mouse release events for the playing scene.
     *
     * @param x The x-coordinate of the mouse release.
     * @param y The y-coordinate of the mouse release.
     */
    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    /**
     * Handles mouse drag events for the playing scene.
     *
     * @param x The x-coordinate of the mouse drag.
     * @param y The y-coordinate of the mouse drag.
     */
    @Override
    public void mouseDragged(int x, int y) {
        // Implementation for mouse drag events goes here
    }

    /**
     * Gets the TowerManager associated with the playing scene.
     *
     * @return The TowerManager instance.
     */
    public TowerManager getTowerManager() {
        return towerManager;
    }

    public void placeTower(int x, int y) {
        if (bottomBar.getSelectedTower() != null) {
            bottomBar.getSelectedTower().setPosition(x, y);
            bottomBar.setSelectedTower(null);
        }
    }

}