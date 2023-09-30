package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import static utilz.Constants.PlayerConstants.ATTACK;
import static utilz.Constants.PlayerConstants.DEATH;
import static utilz.Constants.PlayerConstants.GRAB_ATTACK;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;
import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerConstants.JUMP;
import static utilz.Constants.PlayerConstants.LONG_JUMP;
import static utilz.Constants.PlayerConstants.QUICK_ATTACK;
import static utilz.Constants.PlayerConstants.QUICK_ATTACK_2;
import static utilz.Constants.PlayerConstants.QUICK_ATTACK_3;
import static utilz.Constants.PlayerConstants.ROLLING;
import static utilz.Constants.PlayerConstants.RUNNING;
import static utilz.Constants.PlayerConstants.ULT_DEFENSE;
import utilz.LoadSave;

public class Player extends Entity {

    private BufferedImage[][] animations; // Matriz para armazenar as animações do jogador
    private int playerAction = IDLE; // Ação atual do jogador (parado ou correndo)
    private boolean left, up, right, down; // Variáveis de controle de direção
    private boolean moving = false, attacking = false; // Indica se o jogador está se movendo
    private float playerSpeed = 2.0f; // Velocidade de movimento do jogador
    private int aniTick = 0; // Contador para controlar a velocidade da animação
    private int aniIndex = 0; // Índice atual da animação
    private int aniSpeed = 21; // Define a velocidade da animação (pode ser ajustada conforme necessário)

    public Player(float x, float y) {
        super(x, y);
        loadAnimations(); // Carrega as animações do jogador
    }

    public void update() {
        updatePos(); // Atualiza a posição do jogador
        updateAnimationTick(); // Atualiza o índice da animação
        setAnimation(); // Define a ação de animação com base no movimento
    }

    public void render(Graphics g) {
        // Desenha a imagem da animação atual do jogador
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 256 * 3, 160 * 3, null);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0; // Reinicia a animação se o índice exceder o número de sprites  
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving) {
            playerAction = RUNNING; // Define a ação como "correndo" se o jogador estiver se movendo
        } else {
            playerAction = IDLE; // Caso contrário, define a ação como "parado"
        }
        if (attacking) {
            playerAction = ATTACK;
        }
        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void updatePos() {
        moving = false; // Define o movimento como falso no início

        // Atualiza a posição com base nas teclas pressionadas
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {
            BufferedImage img = LoadSave.GetSpriteLilith(LoadSave.PLAYER_LILITH);
            animations = new BufferedImage[9][6]; // Inicializa a matriz de animações
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    // Divide a imagem em subimagens e armazena na matriz de animações
                    animations[j][i] = img.getSubimage(i * 288, j * 128, 288, 128);
                }
            }

        }

    

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;

    }

    public void setAttacking(boolean attaking) {
        this.attacking = attaking;

    }

    // Métodos getter e setter para as variáveis de direção do jogador
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
}
