package Main.Inputs;

import Main.Game;
import Main.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
	private Game game;

	public MyMouseListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU:
				game.getMenu().mouseMoved(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlaying().mouseMoved(e.getX(), e.getY());
				break;
			case HOWTOPLAY:
				game.getHowToPlay().mouseMoved(e.getX(), e.getY());
				break;
			case PAUSED:
				game.getPause().mouseMoved(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mouseMoved(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mouseMoved(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			switch (GameStates.gameState) {
				case MENU:
					game.getMenu().mouseClicked(e.getX(), e.getY());
					break;
				case PLAYING:
					game.getPlaying().mouseClicked(e.getX(), e.getY());
					break;
				case HOWTOPLAY:
					game.getHowToPlay().mouseClicked(e.getX(), e.getY());
					break;
				case EDIT:
					game.getEditor().mouseClicked(e.getX(), e.getY());
					break;
				case PAUSED:
					game.getPause().mouseClicked(e.getX(), e.getY());
					break;
				case GAMEOVER:
					game.getGameOver().mouseClicked(e.getX(), e.getY());
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU:
				game.getMenu().mousePressed(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlaying().mousePressed(e.getX(), e.getY());
				break;
			case HOWTOPLAY:
				game.getHowToPlay().mousePressed(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mousePressed(e.getX(), e.getY());
				break;
			case PAUSED:
				game.getPause().mousePressed(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mousePressed(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
			case MENU:
				game.getMenu().mouseReleased(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlaying().mouseReleased(e.getX(), e.getY());
				break;
			case HOWTOPLAY:
				game.getHowToPlay().mouseReleased(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mouseReleased(e.getX(), e.getY());
			case PAUSED:
				game.getPause().mouseReleased(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mouseReleased(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
