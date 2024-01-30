package Main.scenes;

import Main.Game;
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

import static Main.GameStates.*;

public class HowToPlay extends GameScene implements SceneMethods {

	private MyButton bMenu;

	public HowToPlay(Game game) {
		super(game);
		initButtons();

	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 50;
		bMenu = new MyButton("Menu", x, y, w, h);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getBg(), 0, 0, null);
		drawButtons(g);
		displayInstructions(g);
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
		bMenu.draw(g);
	}
	
	private void displayInstructions(Graphics g) {
		int x = 640 / 2; 
		int y = 200;
		int lineHeight = 40; 
		FontMetrics fontMetrics = g.getFontMetrics();

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		String[] instructions = {
				"Welcome to Tower Defense!",
				"Your goal is to defend the castle from incoming monsters.",
				"Place towers along the path to attack the monsters.",
				"If a monster reaches the castle, your castle loses health!",
				"When the castle's health reaches 0, you lose the game.",
				"Use gold to buy and upgrade towers.",
				"Earn gold by defeating monsters. You start with 150 gold.",
				"Good luck, Commander!"
		};

		for (String line : instructions) {
			int stringWidth = fontMetrics.stringWidth(line);
			int lineX = x - stringWidth / 2;
			g.drawString(line, lineX, y);
			y += lineHeight;
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			setGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {

	}

}