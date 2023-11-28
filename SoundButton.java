package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton {

    // Matriz de imagens para representar diferentes estados do botão de som
    private BufferedImage[][] soundImgs;
    
    // Flags para indicar se o mouse está sobre o botão e se está pressionado
    private boolean mouseOver, mousePressed;
    
    // Flag para indicar se o som está mutado
    private boolean muted;
    
    // Índices para a linha e a coluna da matriz de imagens
    private int rowIndex, colIndex;

    // Construtor do SoundButton
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        // Carrega as imagens do botão de som
        loadSoundImgs();
    }

    // Carrega as imagens do botão de som
    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i < soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
    }

    // Atualiza os índices com base no estado do botão
    public void update() {
        // Define a linha com base no estado de som (mudo ou não mudo)
        if (muted)
            rowIndex = 1;
        else
            rowIndex = 0;

        // Define a coluna com base no estado do mouse (normal, mouseOver, mousePressed)
        colIndex = 0;
        if (mouseOver)
            colIndex = 1;
        if (mousePressed)
            colIndex = 2;
    }

    // Reseta as flags de mouseOver e mousePressed
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Desenha o botão de som
    public void draw(Graphics g) {
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
    }

    // Getters e setters para as flags de mouseOver e mousePressed
    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // Getters e setters para a flag de som mutado
    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
