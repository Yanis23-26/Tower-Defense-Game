package Main.scenes;

import Main.Game;
import Main.ui.MyButton;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

    private MyButton bPlaying, bEdit, bHowToPlay, bQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Edit", x, y + yOffset, w, h);
        bHowToPlay = new MyButton("How To Play", x, y + yOffset * 2, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getMainBg(), 0, 0, null);
        drawButtons(g);
    }

    public BufferedImage getMainBg() {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("/res/Backgrounds/TD_Main_Bg.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bHowToPlay.draw(g);
        bQuit.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y))
            setGameState(PLAYING);
        else if (bEdit.getBounds().contains(x, y))
            setGameState(EDIT);
        else if (bHowToPlay.getBounds().contains(x, y))
            setGameState(HOWTOPLAY);
        else if (bQuit.getBounds().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bHowToPlay.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y))
            bPlaying.setMouseOver(true);
        else if (bEdit.getBounds().contains(x, y))
            bEdit.setMouseOver(true);
        else if (bHowToPlay.getBounds().contains(x, y))
            bHowToPlay.setMouseOver(true);
        else if (bQuit.getBounds().contains(x, y))
            bQuit.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {

        if (bPlaying.getBounds().contains(x, y))
            bPlaying.setMousePressed(true);
        else if (bEdit.getBounds().contains(x, y))
            bEdit.setMousePressed(true);
        else if (bHowToPlay.getBounds().contains(x, y))
            bHowToPlay.setMousePressed(true);
        else if (bQuit.getBounds().contains(x, y))
            bQuit.setMousePressed(true);

    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bHowToPlay.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

}
