package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;

public class PauseOverlay {

    // Referência para o estado de jogo Playing
    private Playing playing;

    // Imagem de fundo para a sobreposição de pausa
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;

    // Botões de som e UrmButtons
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;

    // Botão de volume
    private VolumeButton volumeButton;

    // Construtor da PauseOverlay
    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();

        createSoundButtons();
        createUrmButtons();
        createVolumeButton();
    }

    // Método para criar o botão de volume
    private void createVolumeButton() {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    // Método para criar os botões Urm
    private void createUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    // Método para criar os botões de som
    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    // Método para carregar a imagem de fundo
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    // Método para atualizar os componentes da PauseOverlay
    public void update() {
        musicButton.update();
        sfxButton.update();

        menuB.update();
        replayB.update();
        unpauseB.update();

        volumeButton.update();
    }

    // Método para desenhar os componentes da PauseOverlay
    public void draw(Graphics g) {
        // Desenha o fundo
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        // Desenha os botões de som
        musicButton.draw(g);
        sfxButton.draw(g);

        // Desenha os botões UrmButtons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

        // Desenha o botão de volume
        volumeButton.draw(g);
    }

    // Métodos para manipulação de eventos do mouse
    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isIn(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        } else if (isIn(e, menuB)) {
            menuB.setMousePressed(true);
        } else if (isIn(e, replayB)) {
            replayB.setMousePressed(true);
        } else if (isIn(e, unpauseB)) {
            unpauseB.setMousePressed(true);
        } else if (isIn(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        handleMouseReleased(e);
        resetButtonStates();
    }

    public void mouseMoved(MouseEvent e) {
        handleMouseMoved(e);
        resetButtonStates();
    }

    // Método para verificar se o mouse está dentro de um botão
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Método para tratar o evento de liberação do mouse
    private void handleMouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }

        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        } else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                playing.resetAll();
                playing.unpauseGame();
            }
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
            }
        }
    }

    // Método para tratar o evento de movimento do mouse
    private void handleMouseMoved(MouseEvent e) {
        musicButton.setMouseOver(isIn(e, musicButton));
        sfxButton.setMouseOver(isIn(e, sfxButton));
        menuB.setMouseOver(isIn(e, menuB));
        replayB.setMouseOver(isIn(e, replayB));
        unpauseB.setMouseOver(isIn(e, unpauseB));
        volumeButton.setMouseOver(isIn(e, volumeButton));
    }

    // Método para resetar os estados dos botões
    private void resetButtonStates() {
        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
        volumeButton.resetBools();
    }

}
