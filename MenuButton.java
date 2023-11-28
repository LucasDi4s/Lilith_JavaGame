package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {

    // Coordenadas do botão
    private int xPos, yPos;

    // Índice da linha e índice do botão atual
    private int rowIndex, index;

    // Deslocamento para centralizar o botão
    private int xOffsetCenter = B_WIDTH / 2;

    // Estado associado ao botão
    private Gamestate state;

    // Imagens para diferentes estados do botão
    private BufferedImage[] imgs;

    // Flags para controle de interação do mouse
    private boolean mouseOver, mousePressed;

    // Área de colisão representada como um retângulo
    private Rectangle bounds;

    // Construtor da classe
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs(); // Carrega as imagens associadas ao botão
        initBounds(); // Inicializa a área de colisão
    }

    // Método privado para inicializar a área de colisão
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    // Método privado para carregar as imagens associadas ao botão
    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    // Método para desenhar o botão
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    // Método para atualizar o estado do botão
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    // Getters e setters
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

    public Rectangle getBounds() {
        return bounds;
    }

    // Método para aplicar o estado associado ao botão
    public void applyGamestate() {
        Gamestate.state = state;
    }

    // Método para redefinir as flags de interação do mouse
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
