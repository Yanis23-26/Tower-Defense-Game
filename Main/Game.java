package Main;

import Main.scenes.Playing;
import Main.scenes.HowToPlay;
import Main.Castle.Castle;
import Main.Player.Player;
import Main.ScoreBoard.ScoreBoard;
import Main.helpers.LoadSave;
import Main.managers.TileManager;
import Main.scenes.Editing;
import Main.scenes.GameOver;
import Main.scenes.Menu;
import Main.scenes.Pause;

import static Main.GameStates.MENU;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Thread gameThread;

    private Render render;
    private Menu menu;
    private Playing playing;
    private HowToPlay howtoplay;
    private Editing editing;
    private Pause pause;
    private GameOver gameOver;
    private Castle castle;
    private int reset = 0;
    private String namePlayer = "";
    private ScoreBoard scoreBoard;

    private TileManager tileManager;
    private Player player;

    public Game() {
        initClasses();
        createDefaultLevel();

        setTitle("Tower Defense Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(gameScreen);
        pack();

        setVisible(true);
        getUsername();
        if (namePlayer.equals("")) {
            player.setName("Player");
        } else {
            player.setName(namePlayer);
        }
    }

    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameOver = new GameOver(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        pause = new Pause(this);
        castle = new Castle("Castle");
        player = new Player("", castle);
        playing = new Playing(this, player, castle);
        editing = new Editing(this);
        howtoplay = new HowToPlay(this);
        scoreBoard = new ScoreBoard();
    }

    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void updateGame() {
        switch (GameStates.gameState) {
            case EDIT:
                break;
            case MENU:
                break;
            case PLAYING:
                playing.update();
                if (playing.getReset() == 1) {
                    this.reset = 1;
                }
                break;
            case HOWTOPLAY:
                break;
            case GAMEOVER:
                break;
            default:
                break;
        }
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 0;

        LoadSave.CreateLevel("new_level", arr);

    }

    private void getUsername() {
        JTextField nameResult = new JTextField();
        Object[] message = { "Please enter your name:", nameResult };
        int result = JOptionPane.showConfirmDialog(this, message, "Enter your name", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            namePlayer = nameResult.getText();
            JOptionPane.showMessageDialog(this, "Welcome, " + namePlayer);
        } else {
            namePlayer = "";
        }
        System.out.println(namePlayer);
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / 120.0;
        double timePerUpdate = 1000000000.0 / 60.0;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {
            now = System.nanoTime();
            // render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }
            // update
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }
            // checkFPS and UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                // System.out.println("FPS:" + frames + " UPS:" + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

            if (this.reset == 1) {
            castle = new Castle("Castle");
            player = new Player(this.namePlayer, castle);
            playing = new Playing(this, player, castle);
            reset = 0;
            }

            if (castle.getHp() == 0) {
                castle = new Castle("Castle");
                player = new Player(this.namePlayer, castle);
                playing = new Playing(this, player, castle);
                reset = 0;
                scoreBoard.writeAndOrder(player.getName(), player.getScore());
            }
        }
    }

    // getters and setters
    public Render getRender() {
        return this.render;
    }

    public GameOver getGameOver() {
        return this.gameOver;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Playing getPlaying() {
        return this.playing;
    }

    public HowToPlay getHowToPlay() {
        return this.howtoplay;
    }

    public Editing getEditor() {
        return editing;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Pause getPause() {
        return pause;
    }

    public static void main(String[] args) {
        System.out.println("Starting the game...");
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }
}
