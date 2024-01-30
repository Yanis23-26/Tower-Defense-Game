package Main.ui;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents a generic bar with basic functionality for UI elements.
 */
public class Bar {

    protected int x, y, width, height;

    /**
     * Initializes the bar with specified coordinates and dimensions.
     *
     * @param x      The x-coordinate of the bar.
     * @param y      The y-coordinate of the bar.
     * @param width  The width of the bar.
     * @param height The height of the bar.
     */
    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the feedback for a button, considering mouse-over and mouse-pressed states.
     *
     * @param g The Graphics object to draw on.
     * @param b The MyButton object for which the feedback is drawn.
     */
    protected void drawButtonFeedback(Graphics g, MyButton b) {
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