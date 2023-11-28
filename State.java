package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {

    // Referência para a instância do jogo
    protected Game game;

    // Construtor da classe
    public State(Game game) {
        this.game = game;
    }

    // Método para verificar se o mouse está sobre um botão do menu
    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    // Método para obter a instância do jogo
    public Game getGame() {
        return game;
    }
}
