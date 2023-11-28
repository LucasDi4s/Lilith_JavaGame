package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

/**
 * Representa um recipiente no jogo, como uma caixa ou barril.
 */
public class GameContainer extends GameObject {

    /**
     * Construtor da classe GameContainer.
     *
     * @param x A posição X do recipiente.
     * @param y A posição Y do recipiente.
     * @param objType O tipo do recipiente.
     */
    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    /**
     * Cria a hitbox do recipiente com base no tipo (caixa ou barril).
     */
    private void createHitbox() {
        if (objType == BOX) {
            initHitbox(25, 18);

            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);

        } else {
            initHitbox(23, 25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }

        // Ajusta a posição da hitbox com base nos deslocamentos e escala do jogo.
        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;
    }

    /**
     * Atualiza o recipiente, aplicando animação se necessário.
     */
    public void update() {
        if (doAnimation) {
            updateAnimationTick();
        }
    }
}
