package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;

/**
 * Gerenciador de níveis responsável por carregar, manter e alternar entre os níveis do jogo.
 */
public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private int lvlIndex = 0;

    /**
     * Construtor do LevelManager.
     *
     * @param game A instância do jogo.
     */
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levels = new ArrayList<>();
        buildAllLevels();
    }

    /**
     * Carrega o próximo nível. Se não houver mais níveis, retorna ao menu principal.
     */
    public void loadNextLevel() {
        lvlIndex++;
        if (lvlIndex >= levels.size()) {
            lvlIndex = 0;
            System.out.println("No more levels! Game Completed!");
            Gamestate.state = Gamestate.MENU;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    /**
     * Constrói todos os níveis do jogo.
     */
    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        for (BufferedImage img : allLevels)
            levels.add(new Level(img));
    }

    /**
     * Importa os sprites externos usados para os níveis.
     */
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
    }

    /**
     * Desenha o nível atual na tela, levando em consideração o deslocamento horizontal.
     *
     * @param g         O objeto Graphics para desenho.
     * @param lvlOffset O deslocamento horizontal do nível.
     */
    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    /**
     * Atualiza o gerenciador de níveis (não há lógica de atualização no momento).
     */
    public void update() {

    }

    /**
     * Obtém o nível atual.
     *
     * @return O objeto Level representando o nível atual.
     */
    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }

    /**
     * Obtém a quantidade total de níveis.
     *
     * @return O número total de níveis.
     */
    public int getAmountOfLevels() {
        return levels.size();
    }
}
