package objects;

import main.Game;

/**
 * Representa um objeto Spike no jogo, uma subclasse de GameObject.
 */
public class Spike extends GameObject {

    /**
     * Construtor da classe Spike.
     *
     * @param x      A coordenada x inicial do Spike.
     * @param y      A coordenada y inicial do Spike.
     * @param objType O tipo de objeto Spike.
     */
    public Spike(int x, int y, int objType) {
        super(x, y, objType);

        // Inicializa a hitbox do Spike com largura 32 e altura 16.
        initHitbox(32, 16);

        // Define os deslocamentos de desenho (offset) nas coordenadas x e y.
        xDrawOffset = 0;
        yDrawOffset = (int) (Game.SCALE * 16);
        
        // Adiciona o deslocamento yDrawOffset à coordenada y da hitbox.
        hitbox.y += yDrawOffset;
    }
}
