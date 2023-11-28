package utilz;

import static utilz.Constants.EnemyConstants.CRABBY;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Crabby;
import main.Game;
import main.ScoreManager;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;

/**
 * Métodos auxiliares para funcionalidades diversas no jogo.
 */
public class HelpMethods {

    /**
     * Verifica se é possível mover-se para a posição especificada nas
     * coordenadas (x, y) com a largura e altura fornecidas.
     *
     * @param x A coordenada x.
     * @param y A coordenada y.
     * @param width A largura do objeto.
     * @param height A altura do objeto.
     * @param lvlData Os dados do nível que definem a solidez das posições.
     * @return True se o movimento for possível, False caso contrário.
     */
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData)) {
            if (!IsSolid(x + width, y + height, lvlData)) {
                if (!IsSolid(x + width, y, lvlData)) {
                    if (!IsSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Verifica se a posição (x, y) é sólida com base nos dados do nível.
     *
     * @param x A coordenada x.
     * @param y A coordenada y.
     * @param lvlData Os dados do nível que definem a solidez das posições.
     * @return True se a posição for sólida, False caso contrário.
     */
    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWidth) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

    /**
     * Verifica se a posição do azulejo (xTile, yTile) é sólida com base nos
     * dados do nível.
     *
     * @param xTile A coordenada x do azulejo.
     * @param yTile A coordenada y do azulejo.
     * @param lvlData Os dados do nível que definem a solidez das posições.
     * @return True se a posição do azulejo for sólida, False caso contrário.
     */
    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
        int value = lvlData[yTile][xTile];

        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }
        return false;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Obtém a posição x da entidade próxima à parede, considerando a velocidade
     * horizontal.
     *
     * @param hitbox A caixa de colisão da entidade.
     * @param xSpeed A velocidade horizontal da entidade.
     * @return A posição x ajustada da entidade.
     */
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            // Direção para a direita
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else // Direção para a esquerda
        {
            return currentTile * Game.TILES_SIZE;
        }
    }

    /**
     * Obtém a posição Y da entidade sob o teto ou acima do chão, considerando a
     * caixa de colisão e a velocidade vertical.
     *
     * @param hitbox A caixa de colisão da entidade.
     * @param airSpeed A velocidade vertical da entidade (positiva para queda,
     * negativa para salto).
     * @return A posição Y ajustada da entidade.
     */
    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Caindo - tocando o chão
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else // Pulando
        {
            return currentTile * Game.TILES_SIZE;
        }
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Verifica se a entidade está no chão com base em sua caixa de colisão e
     * nos dados do nível.
     *
     * @param hitbox A caixa de colisão da entidade.
     * @param lvlData Os dados do nível que definem a solidez das posições.
     * @return True se a entidade estiver no chão, False caso contrário.
     */
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se há um chão (piso) na posição abaixo da caixa de colisão da
     * entidade, levando em consideração a velocidade horizontal.
     *
     * @param hitbox A caixa de colisão da entidade.
     * @param xSpeed A velocidade horizontal da entidade.
     * @param lvlData Dados do nível que indicam a solidez dos blocos.
     * @return True se houver um chão abaixo da entidade, False caso contrário.
     */
    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        if (xSpeed > 0) {
            // Se movendo para a direita, verifica a solidez à frente e abaixo.
            return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
        } else {
            // Se movendo para a esquerda, verifica a solidez à frente e abaixo.
            return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
        }
    }

    /**
     * Verifica se todos os azulejos em uma linha horizontal específica são
     * transitáveis.
     *
     * @param xStart A coordenada x de início.
     * @param xEnd A coordenada x de fim.
     * @param y A coordenada y.
     * @param lvlData Os dados do nível que definem a solidez das posições.
     * @return True se todos os azulejos forem transitáveis, False caso
     * contrário.
     */
    public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
        for (int i = 0; i < xEnd - xStart; i++) {
            if (IsTileSolid(xStart + i, y, lvlData)) {
                return false;
            }
            if (!IsTileSolid(xStart + i, y + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Verifica se a linha de visão entre duas entidades é clara, com base nos
     * dados do nível.
     *
     * @param lvlData Os dados do nível que definem a solidez das posições.
     * @param firstHitbox A caixa de colisão da primeira entidade.
     * @param secondHitbox A caixa de colisão da segunda entidade.
     * @param yTile A coordenada y do azulejo.
     * @return True se a linha de visão for clara, False caso contrário.
     */
    public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
        int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
        int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

        if (firstXTile > secondXTile) {
            return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
        } else {
            return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
        }
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Converte uma imagem de nível em uma matriz de dados do nível.
     *
     * @param img A imagem do nível.
     * @return Uma matriz de dados do nível.
     */
    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Obtém uma lista de caranguejos (Crabby) com base na imagem do nível e no
     * gerenciador de pontuação.
     *
     * @param img A imagem do nível.
     * @param seuScoreManager O gerenciador de pontuação do jogo.
     * @return Uma lista de caranguejos.
     */
    public static ArrayList<Crabby> GetCrabs(BufferedImage img, ScoreManager seuScoreManager) {
        ArrayList<Crabby> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == CRABBY) {
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE, seuScoreManager));
                }
            }
        }
        return list;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Obtém a posição de spawn do jogador com base na imagem do nível.
     *
     * @param img A imagem do nível.
     * @return Um ponto representando a posição de spawn do jogador.
     */
    public static Point GetPlayerSpawn(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 100) {
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
                }
            }
        }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Obtém uma lista de poções com base na imagem do nível.
     *
     * @param img A imagem do nível.
     * @return Uma lista de poções.
     */
    public static ArrayList<Potion> GetPotions(BufferedImage img) {
        ArrayList<Potion> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value == RED_POTION || value == BLUE_POTION) {
                    list.add(new Potion(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }
            }
        }
        return list;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Obtém uma lista de contêineres de jogo com base na imagem do nível.
     *
     * @param img A imagem do nível.
     * @return Uma lista de contêineres de jogo.
     */
    public static ArrayList<GameContainer> GetContainers(BufferedImage img) {
        ArrayList<GameContainer> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value == BOX || value == BARREL) {
                    list.add(new GameContainer(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }
            }
        }
        return list;
    }

    // ... (continuação dos comentários abaixo)
    /**
     * Obtém uma lista de espinhos com base na imagem do nível.
     *
     * @param img A imagem do nível.
     * @return Uma lista de espinhos.
     */
    public static ArrayList<Spike> GetSpikes(BufferedImage img) {
        ArrayList<Spike> list = new ArrayList<>();

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value == SPIKE) {
                    list.add(new Spike(i * Game.TILES_SIZE, j * Game.TILES_SIZE, SPIKE));
                }
            }
        }

        return list;
    }

    // ... (fim dos comentários)
}
