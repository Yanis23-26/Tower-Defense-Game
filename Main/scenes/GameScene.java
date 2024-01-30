package Main.scenes;

import Main.Game;

public class GameScene {
    protected Game game;
    public Game getGame() {
        return game;
    }
    public GameScene(Game game){
        this.game = game;
    }
}
