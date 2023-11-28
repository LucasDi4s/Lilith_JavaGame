package ui;

import java.awt.Rectangle;

public class PauseButton {

    // Coordenadas e dimensões do botão
    protected int x, y, width, height;

    // Área de colisão (bounds) representada como um retângulo
    protected Rectangle bounds;

    // Construtor da classe
    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds(); // Chama o método para criar a área de colisão
    }

    // Método privado para criar a área de colisão
    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    // Getters e setters para as propriedades do botão
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        updateBounds();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        updateBounds();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updateBounds();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateBounds();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    // Método para atualizar a área de colisão quando as propriedades do botão são alteradas
    private void updateBounds() {
        bounds.setBounds(x, y, width, height);
    }
}
