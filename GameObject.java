package objects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

/**
 * Representa um objeto genérico no jogo, como recipientes, poções, etc.
 */
public class GameObject {

    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;

    /**
     * Construtor da classe GameObject.
     *
     * @param x A posição X do objeto.
     * @param y A posição Y do objeto.
     * @param objType O tipo do objeto.
     */
    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    /**
     * Atualiza a contagem de animação do objeto.
     */
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;
                if (objType == BARREL || objType == BOX) {
                    doAnimation = false;
                    active = false;
                }
            }
        }
    }

    /**
     * Reseta os valores relacionados à animação do objeto.
     */
    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        if (objType == BARREL || objType == BOX) {
            doAnimation = false;
        } else {
            doAnimation = true;
        }
    }

    /**
     * Inicializa a hitbox do objeto.
     *
     * @param width A largura da hitbox.
     * @param height A altura da hitbox.
     */
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    /**
     * Desenha a hitbox do objeto (usado para depuração).
     *
     * @param g O objeto Graphics para desenho.
     * @param xLvlOffset O deslocamento horizontal do nível.
     */
    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    /**
     * Obtém o tipo do objeto.
     *
     * @return O tipo do objeto.
     */
    public int getObjType() {
        return objType;
    }

    /**
     * Obtém a hitbox do objeto.
     *
     * @return A hitbox do objeto.
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    /**
     * Verifica se o objeto está ativo.
     *
     * @return true se o objeto estiver ativo, false caso contrário.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Define se o objeto está ativo.
     *
     * @param active O novo estado de ativação do objeto.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Define se o objeto está em animação.
     *
     * @param doAnimation true se o objeto deve estar em animação, false caso
     * contrário.
     */
    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    /**
     * Obtém o deslocamento horizontal para desenho.
     *
     * @return O deslocamento horizontal para desenho.
     */
    public int getxDrawOffset() {
        return xDrawOffset;
    }

    /**
     * Obtém o deslocamento vertical para desenho.
     *
     * @return O deslocamento vertical para desenho.
     */
    public int getyDrawOffset() {
        return yDrawOffset;
    }

    /**
     * Obtém o índice de animação do objeto.
     *
     * @return O índice de animação do objeto.
     */
    public int getAniIndex() {
        return aniIndex;
    }
}
