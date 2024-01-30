package Main.ui;

import static Main.GameStates.MENU;
import static Main.GameStates.PAUSED;
import static Main.GameStates.setGameState;

import Main.scenes.Playing;
import Main.Tower.*;
import Main.helpers.LoadSave;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Represents the action bar UI element for the Playing scene.
 */
public class ActionBar extends Bar {

    // Contains all buttons to create Towers
    private MyButton[] towerButtons;

    private ATower selectedTower;
    private Playing playing;
    private MyButton bMenu;
    private MyButton bPause;
    private int reset = 0;
    private int lastTowerX, lastTowerY, lastTowerId;
    private int[][] lvl;

    // Getter
    public int getReset() {
        return this.reset;
    }

    public MyButton[] getTowerButtons() {
        return towerButtons;
    }

    public ATower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(ATower tower) {
        this.selectedTower = tower;
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
    }

    /**
     * Initializes the action bar with specified coordinates, dimensions, and the
     * Playing scene.
     *
     * @param x       The x-coordinate of the action bar.
     * @param y       The y-coordinate of the action bar.
     * @param width   The width of the action bar.
     * @param height  The height of the action bar.
     * @param playing The Playing scene associated with the action bar.
     */
    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        initButtons();
        loadDefaultLevel();
    }

    /**
     * Initializes the buttons within the action bar.
     */
    private void initButtons() {
        bPause = new MyButton("Pause", 2, 650, 100, 30);
        bMenu = new MyButton("Menu", 2, 690, 100, 30);
        towerButtons = new MyButton[5]; // 5 dummy buttons
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);
        // create the buttons
        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
        }
    }

    // draw the boutons
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);
        for (MyButton b : towerButtons) {
            b.draw(g);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getTowerID()], b.x, b.y, b.width, b.height, null);
        }
    }

    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(70, 100, 70));
        g.fillRect(x, y, width, height);
        // Draw gold and score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Gold: " + playing.getMonsterManager().getPlayer().getGold(), x + 510, y + 50);
        g.drawString("Score: " + playing.getMonsterManager().getPlayer().getScore(), x + 510, y + 70);
        g.drawString(playing.getMonsterManager().getPlayer().getName(), x + 510, y + 20);
        g.drawString("Wave: " + playing.getMonsterManager().getWave(), x + 510, y + 90);
        // Buttons
        drawButtons(g);
    }

    private void changeTile(int x, int y) {
        if (selectedTower != null) {
            int TowerX = x / 32;
            int TowerY = y / 32;

            if (lastTowerX == TowerX && lastTowerY == TowerY && lastTowerId == selectedTower.getId())
                return;
            lastTowerX = TowerX;
            lastTowerY = TowerY;
            lastTowerId = selectedTower.getId();
            lvl[TowerY][TowerX] = selectedTower.getId();
            selectedTower = null;
        }
    }

    /**
     * Handles the mouse click event within the action bar.
     *
     * @param x The x-coordinate of the mouse click.
     * @param y The y-coordinate of the mouse click.
     */
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
            this.reset = 1;
        }
        if (bPause.getBounds().contains(x, y))
            setGameState(PAUSED);

        if (towerButtons[0].getBounds().contains(x, y)) {
            selectedTower = new NormalTower(x, y);
        }
        if (towerButtons[1].getBounds().contains(x, y)) {
            selectedTower = new FireTower(x, y);
        }
        if (towerButtons[2].getBounds().contains(x, y)) {
            selectedTower = new IceTower(x, y);
        }
        if (towerButtons[3].getBounds().contains(x, y)) {
            selectedTower = new LongRangeTower(x, y);
        }
        if (towerButtons[4].getBounds().contains(x, y)) {
            selectedTower = new ZoneTower(x, y);
        }
        playing.setSelectedTower(selectedTower);
    }

    /**
     * Handles the mouse movement event within the action bar.
     *
     * @param x The x-coordinate of the mouse movement.
     * @param y The y-coordinate of the mouse movement.
     */
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
        if (bPause.getBounds().contains(x, y))
            bPause.setMouseOver(true);
        else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    /**
     * Handles the mouse press event within the action bar.
     *
     * @param x The x-coordinate of the mouse press.
     * @param y The y-coordinate of the mouse press.
     */
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
        if (bPause.getBounds().contains(x, y))
            bPause.setMousePressed(true);
        else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    /**
     * Handles the mouse release event within the action bar.
     *
     * @param x The x-coordinate of the mouse release.
     * @param y The y-coordinate of the mouse release.
     */
    public void mouseReleased(int x, int y) {
        bPause.resetBooleans();
        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
    }

    public void mouseDragged(int x, int y) {
        if (y < 640) {
            changeTile(x, y);
        }

    }
}