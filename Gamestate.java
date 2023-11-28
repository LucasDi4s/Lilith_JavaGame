package gamestates;

// Enum para representar os estados do jogo
public enum Gamestate {
    PLAYING, MENU, OPTIONS, QUIT;

    // Variável estática para rastrear o estado atual do jogo
    public static Gamestate state = MENU;
}
