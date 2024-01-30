package Main.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents a custom button used in the user interface.
 */
public class MyButton {

    public int x, y, width, height;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    private int towerID;

    public int getId() {
        return towerID;
    }

    public int getTowerID() {
        return towerID;
    }

    public void setTowerID(int towerID) {
        this.towerID = towerID;
    }

    /**
     * Constructs a button with the specified text, position, and dimensions.
     *
     * @param text   The text displayed on the button.
     * @param x      The x-coordinate of the button.
     * @param y      The y-coordinate of the button.
     * @param width  The width of the button.
     * @param height The height of the button.
     */
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.towerID = -1;
        initBounds();
    }

    /**
     * Constructs a button with the specified text, position, dimensions, and
     * identifier.
     *
     * @param text   The text displayed on the button.
     * @param x      The x-coordinate of the button.
     * @param y      The y-coordinate of the button.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param id     The identifier associated with the button.
     */
    public MyButton(String text, int x, int y, int width, int height, int towerID) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.towerID = towerID;

        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Draws the button on the specified Graphics object.
     *
     * @param g The Graphics object to draw on.
     */
    public void draw(Graphics g) {
        // Body
        drawBody(g);

        // Border
        drawBorder(g);

        // Text
        drawText(g);
    }

    private void drawBorder(Graphics g) {
        int arcWidth = 35;
        int arcHeight = 35;
        g.setColor(Color.black);
        g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
        if (mousePressed) {
            g.drawRoundRect(x + 1, y + 1, width - 2, height - 2, arcWidth, arcHeight);
            g.drawRoundRect(x + 2, y + 2, width - 4, height - 4, arcWidth, arcHeight);
        }

    }

    private void drawBody(Graphics g) {
        if (mouseOver)
            g.setColor(Color.gray);
        else
            g.setColor(Color.WHITE);

        int arcWidth = 35;
        int arcHeight = 35;
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
    }

    /**
     * Resets the mouse over and mouse pressed states of the button.
     */
    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    /**
     * Sets the mouse pressed state of the button.
     *
     * @param mousePressed True if the mouse is pressed over the button, false
     *                     otherwise.
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Sets the mouse over state of the button.
     *
     * @param mouseOver True if the mouse is over the button, false otherwise.
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Checks if the mouse is over the button.
     *
     * @return True if the mouse is over the button, false otherwise.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Checks if the mouse is pressed over the button.
     *
     * @return True if the mouse is pressed over the button, false otherwise.
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Gets the bounding rectangle of the button.
     *
     * @return The bounding rectangle of the button.
     */
    public Rectangle getBounds() {
        return bounds;
    }

}