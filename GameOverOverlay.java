package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;

public class GameOverOverlay {

    // Referência para o estado de jogo
    private Playing playing;

    // Construtor da classe
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    // Método para desenhar a sobreposição na tela
    public void draw(Graphics g) {
        // Desenha um retângulo preto semi-transparente para cobrir a tela
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Define a cor do texto como branco e desenha as mensagens
        g.setColor(Color.white);
        g.drawString("Game Over", Game.GAME_WIDTH / 2, 150);
        g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH / 2, 300);
    }

    // Método chamado quando uma tecla é pressionada
    public void keyPressed(KeyEvent e) {
        // Se a tecla pressionada for a tecla Esc
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // Reseta o estado de jogo e muda para o estado de menu principal
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
}
