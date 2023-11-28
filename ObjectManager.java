package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Player;
import gamestates.Playing;
import levels.Level;
import main.ScoreManager;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;

/**
 * Gerencia os objetos no jogo, como poções, recipientes e armadilhas.
 */
public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private BufferedImage spikeImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;
    private ScoreManager scoreManager;

    /**
     * Construtor da classe ObjectManager.
     *
     * @param playing A instância do estado de jogo Playing.
     * @param scoreManager O gerenciador de pontuação do jogo.
     */
    public ObjectManager(Playing playing, ScoreManager scoreManager) {
        this.playing = playing;
        this.scoreManager = scoreManager;
        loadImgs();
    }

    /**
     * Verifica se o jogador tocou em alguma armadilha (Spike).
     *
     * @param p O jogador.
     */
    public void checkSpikesTouched(Player p) {
        for (Spike s : spikes) {
            if (s.getHitbox().intersects(p.getHitbox())) {
                p.kill();
            }
        }
    }

    /**
     * Verifica se algum objeto foi tocado pelo jogador.
     *
     * @param hitbox A hitbox do jogador.
     */
    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (Potion p : potions) {
            if (p.isActive()) {
                if (hitbox.intersects(p.getHitbox())) {
                    p.setActive(false);
                    applyEffectToPlayer(p);
                }
            }
        }
    }

    /**
     * Aplica o efeito da poção ao jogador.
     *
     * @param p A poção a ser aplicada.
     */
    public void applyEffectToPlayer(Potion p) {
        if (p.getObjType() == RED_POTION) {
            playing.getPlayer().changeHealth(RED_POTION_VALUE);
        } else {
            playing.getPlayer().changePower(BLUE_POTION_VALUE);
        }
    }

    /**
     * Verifica se algum objeto foi atingido pela área de ataque do jogador.
     *
     * @param attackbox A área de ataque do jogador.
     */
    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for (GameContainer gc : containers) {
            if (gc.isActive() && !gc.doAnimation) {
                if (gc.getHitbox().intersects(attackbox)) {
                    gc.setAnimation(true);
                    int type = 0;
                    if (gc.getObjType() == BARREL) {
                        type = 1;
                    }
                    potions.add(new Potion((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y - gc.getHitbox().height / 2), type));
                    return;
                }
            }
        }
    }

    /**
     * Carrega os objetos do novo nível.
     *
     * @param newLevel O novo nível a ser carregado.
     */
    public void loadObjects(Level newLevel) {
        potions = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
        spikes = newLevel.getSpikes();
    }

    /**
     * Carrega as imagens dos objetos (poções, recipientes e armadilhas).
     */
    private void loadImgs() {
        // Carrega as imagens das poções.
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for (int j = 0; j < potionImgs.length; j++) {
            for (int i = 0; i < potionImgs[j].length; i++) {
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }

        // Carrega as imagens dos recipientes.
        BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];

        for (int j = 0; j < containerImgs.length; j++) {
            for (int i = 0; i < containerImgs[j].length; i++) {
                containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }

        // Carrega a imagem da armadilha (Spike).
        spikeImg = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);
    }

    /**
     * Atualiza o estado dos objetos.
     */
    public void update() {
        for (Potion p : potions) {
            if (p.isActive()) {
                p.update();
            }
        }

        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                gc.update();
            }
        }
    }

    /**
     * Desenha os objetos na tela.
     *
     * @param g O objeto Graphics para desenho.
     * @param xLvlOffset O deslocamento horizontal do nível.
     */
    public void draw(Graphics g, int xLvlOffset) {
        drawPotions(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
        drawTraps(g, xLvlOffset);
    }

    /**
     * Desenha as armadilhas (Spikes) na tela.
     *
     * @param g O objeto Graphics para desenho.
     * @param xLvlOffset O deslocamento horizontal do nível.
     */
    private void drawTraps(Graphics g, int xLvlOffset) {
        for (Spike s : spikes) {
            g.drawImage(spikeImg, (int) (s.getHitbox().x - xLvlOffset), (int) (s.getHitbox().y - s.getyDrawOffset()), SPIKE_WIDTH, SPIKE_HEIGHT, null);
        }
    }

    /**
     * Desenha os recipientes na tela.
     *
     * @param g O objeto Graphics para desenho.
     * @param xLvlOffset O deslocamento horizontal do nível.
     */
    private void drawContainers(Graphics g, int xLvlOffset) {
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                int type = 0;
                if (gc.getObjType() == BARREL) {
                    type = 1;
                }
                g.drawImage(containerImgs[type][gc.getAniIndex()], (int) (gc.getHitbox().x - gc.getxDrawOffset() - xLvlOffset), (int) (gc.getHitbox().y - gc.getyDrawOffset()), CONTAINER_WIDTH,
                        CONTAINER_HEIGHT, null);
            }
        }
    }

    /**
     * Desenha as poções na tela.
     *
     * @param g O objeto Graphics para desenho.
     * @param xLvlOffset O deslocamento horizontal do nível.
     */
    private void drawPotions(Graphics g, int xLvlOffset) {
        for (Potion p : potions) {
            if (p.isActive()) {
                int type = 0;
                if (p.getObjType() == RED_POTION) {
                    type = 1;
                }
                g.drawImage(potionImgs[type][p.getAniIndex()], (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), (int) (p.getHitbox().y - p.getyDrawOffset()), POTION_WIDTH, POTION_HEIGHT,
                        null);
            }
        }
    }

    /**
     * Reseta todos os objetos para o estado inicial.
     */
    public void resetAllObjects() {
        loadObjects(playing.getLevelManager().getCurrentLevel());
        for (Potion p : potions) {
            p.reset();
        }
        for (GameContainer gc : containers) {
            gc.reset();
        }
    }
}
