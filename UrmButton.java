package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton {

    // Array de imagens para representar diferentes estados do botão URM
    private BufferedImage[] imgs;
    
    // Índice da linha da sprite sheet
    private int rowIndex;
    
    // Índice atual da imagem a ser desenhada
    private int index;
    
    // Flags para indicar se o mouse está sobre o botão e se está pressionado
    private boolean mouseOver, mousePressed;

    // Construtor do UrmButton
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImgs();
    }

    // Carrega as imagens do botão URM
    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
    }

    // Atualiza o índice da imagem com base no estado do botão (normal, mouseOver, mousePressed)
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    // Desenha o botão URM
    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
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
