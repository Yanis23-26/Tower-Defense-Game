package Main.scenes;

import Main.Game;
import Main.ui.MyButton;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Main.GameStates.*;

public class Pause extends GameScene implements SceneMethods {

    private MyButton bPlaying, bhowtoplay, bQuit;

    public Pause(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bPlaying = new MyButton("Resume", x, y + yOffset, w, h);
        bhowtoplay = new MyButton("How To Play", x, y + yOffset * 2, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getBg(), 0, 0, null);
        drawButtons(g);
    }

    public BufferedImage getBg() {
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
        bPlaying.draw(g);
        bhowtoplay.draw(g);
        bQuit.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y))
            setGameState(PLAYING);
        else if (bhowtoplay.getBounds().contains(x, y))
            setGameState(HOWTOPLAY);
        else if (bQuit.getBounds().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bhowtoplay.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y))
            bPlaying.setMouseOver(true);
        else if (bhowtoplay.getBounds().contains(x, y))
            bhowtoplay.setMouseOver(true);
        else if (bQuit.getBounds().contains(x, y))
            bQuit.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y))
            bPlaying.setMousePressed(true);
        else if (bhowtoplay.getBounds().contains(x, y))
            bhowtoplay.setMousePressed(true);
        else if (bQuit.getBounds().contains(x, y))
            bQuit.setMousePressed(true);

    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bhowtoplay.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

}
