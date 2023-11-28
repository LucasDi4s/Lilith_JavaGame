package objects;

import main.Game;

/**
 * Representa um objeto Potion no jogo, uma subclasse de GameObject.
 */
public class Potion extends GameObject {

    // Deslocamento vertical utilizado para simular um movimento de "flutuação".
    private float hoverOffset;

    // Valor máximo do deslocamento de flutuação.
    private int maxHoverOffset, hoverDir = 1;

    /**
     * Construtor da classe Potion.
     *
     * @param x A coordenada x inicial da Potion.
     * @param y A coordenada y inicial da Potion.
     * @param objType O tipo de objeto Potion.
     */
    public Potion(int x, int y, int objType) {
        super(x, y, objType);

        // Ativa a animação para a Potion.
        doAnimation = true;

        // Inicializa a hitbox da Potion com largura 7 e altura 14.
        initHitbox(7, 14);

        // Define os deslocamentos de desenho (offset) nas coordenadas x e y.
        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);

        // Define o valor máximo do deslocamento de flutuação.
        maxHoverOffset = (int) (10 * Game.SCALE);
    }

    /**
     * Atualiza o estado da Potion.
     */
    public void update() {
        // Atualiza o contador de animação.
        updateAnimationTick();

        // Atualiza o movimento de flutuação da Potion.
        updateHover();
    }

    /**
     * Atualiza o movimento de flutuação da Potion.
     */
    private void updateHover() {
        // Atualiza a posição vertical da Potion com base no movimento de flutuação.
        hoverOffset += (0.075f * Game.SCALE * hoverDir);

        // Inverte a direção do movimento de flutuação quando atinge o valor máximo ou mínimo.
        if (hoverOffset >= maxHoverOffset) {
            hoverDir = -1;
        } else if (hoverOffset < 0) {
            hoverDir = 1;
        }

        // Atualiza a coordenada y da hitbox com base no movimento de flutuação.
        hitbox.y = y + hoverOffset;
    }
}
