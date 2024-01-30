package Main.scenes;

import Main.Game;
import Main.ScoreBoard.ScoreBoard;
import Main.ui.MyButton;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static Main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {

    private MyButton bMenu, bQuit;
    private ScoreBoard myScore;

    public GameOver(Game game) {
        super(game);
        initButtons();
        myScore = new ScoreBoard();
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int xM = 390 / 2 - w / 2;
        int xQ = 890 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bMenu = new MyButton("Menu", xM, y + yOffset, w, h);
        bQuit = new MyButton("Quit", xQ, y + yOffset, w, h);
    }

    private void displayScoreBoard(Graphics g) {
        int x = 600 / 2;
        int y = 400;
        int lineHeight = 40;
        FontMetrics fontMetrics = g.getFontMetrics();

        String scoreOfPlayer = "Your score : " + game.getPlayer().getScore();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(scoreOfPlayer, 245, 150);

        ArrayList<String> records = myScore.ShowRecords("./scoreboard.txt");

        String[] instructions = {
                "Scoreboard : ",
        };

        for (String line : instructions) {
            int stringWidth = fontMetrics.stringWidth(line);
            int lineX = x - stringWidth / 2;
            g.drawString(line, lineX, y);
            y += lineHeight;
        }

        for (String line : records) {
            int stringWidth = fontMetrics.stringWidth(line);
            int lineX = x - stringWidth / 2;
            g.drawString(line, lineX, y);
            y += lineHeight;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getMainBg(), 0, 0, null);
        drawButtons(g);

        displayScoreBoard(g);
    }

    public BufferedImage getMainBg() {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("/res/Backgrounds/pause_bg.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bQuit.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            setGameState(MENU);
        else if (bQuit.getBounds().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bQuit.getBounds().contains(x, y))
            bQuit.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bQuit.getBounds().contains(x, y))
            bQuit.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bMenu.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

}
