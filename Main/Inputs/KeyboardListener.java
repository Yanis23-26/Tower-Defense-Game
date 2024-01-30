package Main.Inputs;

import static Main.GameStates.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            gameState = MENU;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            gameState = PAUSED;
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            gameState = GAMEOVER;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gameState = HOWTOPLAY;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gameState = PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
