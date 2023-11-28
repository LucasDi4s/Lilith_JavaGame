package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Crabby;
import main.Game;
import main.ScoreManager;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utilz.HelpMethods;

import static utilz.HelpMethods.GetLevelData;
import static utilz.HelpMethods.GetCrabs;
import static utilz.HelpMethods.GetPlayerSpawn;

/**
 * Representa um nível no jogo, contendo informações sobre inimigos, poções,
 * containers, spikes, dados do nível, etc.
 */
public class Level {

    private BufferedImage img;
    private int[][] lvlData;
    private ArrayList<Crabby> crabs;
    private ArrayList<Potion> potions;
    private ArrayList<Spike> spikes;
    private ArrayList<GameContainer> containers;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point playerSpawn;

    /**
     * Construtor da classe Level. Inicializa as informações do nível com base
     * na imagem fornecida.
     *
     * @param img A imagem que representa o nível.
     */
    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createEnemies();
        createPotions();
        createContainers();
        createSpikes();
        calcLvlOffsets();
        calcPlayerSpawn();
    }

    /**
     * Cria os objetos de spike com base na imagem do nível.
     */
    private void createSpikes() {
        spikes = HelpMethods.GetSpikes(img);
    }

    /**
     * Cria os objetos de container com base na imagem do nível.
     */
    private void createContainers() {
        containers = HelpMethods.GetContainers(img);
    }

    /**
     * Cria os objetos de poção com base na imagem do nível.
     */
    private void createPotions() {
        potions = HelpMethods.GetPotions(img);
    }

    /**
     * Calcula a posição de spawn do jogador com base na imagem do nível.
     */
    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    /**
     * Calcula os deslocamentos do nível e a quantidade máxima de deslocamento
     * horizontal.
     */
    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    /**
     * Cria os objetos de inimigo (Crabby) com base na imagem do nível.
     */
    private void createEnemies() {
        ScoreManager scoreManager = new ScoreManager();
        crabs = GetCrabs(img, scoreManager);
    }

    /**
     * Cria a matriz de dados do nível com base na imagem do nível.
     */
    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    /**
     * Obtém o índice do sprite na posição (x, y) da matriz de dados do nível.
     *
     * @param x A coordenada x.
     * @param y A coordenada y.
     * @return O índice do sprite na posição (x, y).
     */
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    /**
     * Obtém os dados do nível, representados por uma matriz.
     *
     * @return A matriz de dados do nível.
     */
    public int[][] getLevelData() {
        return lvlData;
    }

    /**
     * Obtém a quantidade máxima de deslocamento horizontal do nível.
     *
     * @return A quantidade máxima de deslocamento horizontal.
     */
    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    /**
     * Obtém a lista de objetos Crabby (inimigos) no nível.
     *
     * @return A lista de objetos Crabby.
     */
    public ArrayList<Crabby> getCrabs() {
        return crabs;
    }

    /**
     * Obtém a posição de spawn do jogador no nível.
     *
     * @return A posição de spawn do jogador como um objeto Point.
     */
    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    /**
     * Obtém a lista de objetos Potion no nível.
     *
     * @return A lista de objetos Potion.
     */
    public ArrayList<Potion> getPotions() {
        return potions;
    }

    /**
     * Obtém a lista de objetos GameContainer no nível.
     *
     * @return A lista de objetos GameContainer.
     */
    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    /**
     * Obtém a lista de objetos Spike no nível.
     *
     * @return A lista de objetos Spike.
     */
    public ArrayList<Spike> getSpikes() {
        return spikes;
    }
}
