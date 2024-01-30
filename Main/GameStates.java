package Main;

public enum GameStates {
    PLAYING,
    MENU,
    EDIT,
    HOWTOPLAY,
    PAUSED,
    GAMEOVER;
    
    public static GameStates gameState = MENU;

    public static void setGameState(GameStates state) {
        gameState = state;
    }
}