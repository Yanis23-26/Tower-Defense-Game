package Main.ui;

import static Main.GameStates.MENU;
import static Main.GameStates.setGameState;

import Main.objects.Tile;
import Main.scenes.Editing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Represents the toolbar UI element for the Editing scene.
 */
public class Toolbar extends Bar {
    private Editing editing;
    private MyButton bMenu, bSave;
    private Tile selectedTile;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    /**
     * Initializes the toolbar with specified coordinates, dimensions, and the Editing scene.
     *
     * @param x       The x-coordinate of the toolbar.
     * @param y       The y-coordinate of the toolbar.
     * @param width   The width of the toolbar.
     * @param height  The height of the toolbar.
     * @param editing The Editing scene associated with the toolbar.
     */
    public Toolbar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    /**
     * Initializes the buttons within the toolbar, including menu, save, and tile buttons.
     */
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bSave = new MyButton("Save", 2, 674, 100, 30);

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);

        int i = 0;
        for (Tile tile : editing.getGame().getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }
    }

    /**
     * Saves the current level in the Editing scene.
     */
    private void saveLevel() {
        editing.saveLevel();
    }

    /**
     * Draws the entire toolbar, including background, buttons, and selected tile.
     *
     * @param g The Graphics object to draw on.
     */
    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
    }

    /**
     * Draws all buttons within the toolbar, including menu, save, tile buttons, and the selected tile.
     *
     * @param g The Graphics object to draw on.
     */
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);

        drawTileButtons(g);
        drawSelectedTile(g);
    }

    /**
     * Draws the selected tile on the toolbar.
     *
     * @param g The Graphics object to draw on.
     */
    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
            g.setColor(Color.black);
            g.drawRect(550, 650, 50, 50);
        }
    }

    /**
     * Draws the tile buttons within the toolbar.
     *
     * @param g The Graphics object to draw on.
     */
    private void drawTileButtons(Graphics g) {
        for (MyButton b : tileButtons) {

            // Sprite
            g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

            // MouseOver
            if (b.isMouseOver())
                g.setColor(Color.white);
            else
                g.setColor(Color.BLACK);

            // Border
            g.drawRect(b.x, b.y, b.width, b.height);

            // MousePressed
            if (b.isMousePressed()) {
                g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
                g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
            }
        }
    }

    /**
     * Gets the image associated with a button id.
     *
     * @param id The id of the button.
     * @return The BufferedImage associated with the button id.
     */
    public BufferedImage getButtImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    /**
     * Handles the mouse click event within the toolbar.
     *
     * @param x The x-coordinate of the mouse click.
     * @param y The y-coordinate of the mouse click.
     */
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            setGameState(MENU);
        else if (bSave.getBounds().contains(x, y))
            saveLevel();
        else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = editing.getGame().getTileManager().getTile(b.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    /**
     * Handles the mouse movement event within the toolbar.
     *
     * @param x The x-coordinate of the mouse movement.
     * @param y The y-coordinate of the mouse movement.
     */
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        for (MyButton b : tileButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMouseOver(true);
        else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    /**
     * Handles the mouse press event within the toolbar.
     *
     * @param x The x-coordinate of the mouse press.
     * @param y The y-coordinate of the mouse press.
     */
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMousePressed(true);
        else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    /**
     * Handles the mouse release event within the toolbar.
     *
     * @param x The x-coordinate of the mouse release.
     * @param y The y-coordinate of the mouse release.
     */
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        for (MyButton b : tileButtons)
            b.resetBooleans();
    }
}