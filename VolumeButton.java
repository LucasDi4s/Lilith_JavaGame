package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {

    // Array de imagens para representar diferentes estados do botão de volume
    private BufferedImage[] imgs;
    
    // Imagem do controle deslizante do volume
    private BufferedImage slider;
    
    // Índice atual da imagem a ser desenhada
    private int index = 0;
    
    // Flags para indicar se o mouse está sobre o botão e se está pressionado
    private boolean mouseOver, mousePressed;
    
    // Posição X do botão, limites mínimo e máximo para o movimento horizontal
    private int buttonX, minX, maxX;

    // Construtor do VolumeButton
    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        minX = x + VOLUME_WIDTH / 2;
        maxX = x + width - VOLUME_WIDTH / 2;
        loadImgs();
    }

    // Carrega as imagens do botão e do controle deslizante do volume
    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);

        slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    // Atualiza o índice da imagem com base no estado do botão (normal, mouseOver, mousePressed)
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    // Desenha o botão de volume e o controle deslizante
    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(imgs[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);
    }

    // Altera a posição X do botão de volume, garantindo que esteja dentro dos limites
    public void changeX(int x) {
        if (x < minX)
            buttonX = minX;
        else if (x > maxX)
            buttonX = maxX;
        else
            buttonX = x;

        bounds.x = buttonX - VOLUME_WIDTH / 2;
    }

    // Reseta as flags de mouseOver e mousePressed
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
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
}
