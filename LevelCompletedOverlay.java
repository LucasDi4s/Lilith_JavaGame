package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class LevelCompletedOverlay {

    // Referência para o estado de jogo
    private Playing playing;

    // Botões para as opções de menu e próximo nível
    private UrmButton menu, next;

    // Imagem de fundo
    private BufferedImage img;

    // Coordenadas e dimensões da imagem de fundo
    private int bgX, bgY, bgW, bgH;

    // Construtor da classe
    public LevelCompletedOverlay(Playing playing) {
        this.playing = playing;
        initImg(); // Inicializa a imagem de fundo
        initButtons(); // Inicializa os botões
    }

    // Método privado para inicializar os botões
    private void initButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int nextX = (int) (445 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    // Método privado para inicializar a imagem de fundo
    private void initImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG);
        bgW = (int) (img.getWidth() * Game.SCALE);
        bgH = (int) (img.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (75 * Game.SCALE);
    }

    // Método para desenhar a sobreposição na tela
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img, bgX, bgY, bgW, bgH, null);
        next.draw(g);
        menu.draw(g);
    }

    // Método para atualizar o estado da sobreposição
    public void update() {
        next.update();
        menu.update();
    }

    // Método para verificar se o mouse está sobre um botão
    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Método chamado quando o mouse é movido
    public void mouseMoved(MouseEvent e) {
        next.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e)) {
            menu.setMouseOver(true);
        } else if (isIn(next, e)) {
            next.setMouseOver(true);
        }
    }

    // Método chamado quando um botão do mouse é solto
    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                playing.resetAll();
                Gamestate.state = Gamestate.MENU;
            }
        } else if (isIn(next, e)) {
            if (next.isMousePressed()) {
                playing.loadNextLevel();
            }
        }

        menu.resetBools();
        next.resetBools();
    }

    // Método chamado quando um botão do mouse é pressionado
    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e)) {
            menu.setMousePressed(true);
        } else if (isIn(next, e)) {
            next.setMousePressed(true);
        }
    }
}
