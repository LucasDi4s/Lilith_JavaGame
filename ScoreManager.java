package main;

/**
 * Classe que gerencia a pontuação do jogo.
 */
public class ScoreManager {

    /**
     * Armazena a pontuação total do jogo.
     */
    private int totalScore = 0;

    /**
     * Obtém a pontuação total do jogo.
     *
     * @return A pontuação total.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Adiciona uma pontuação ao total do jogo.
     *
     * @param score A pontuação a ser adicionada.
     */
    public void addToTotalScore(int score) {
        totalScore += score;
    }

    /**
     * Reseta a pontuação total para zero.
     */
    public void resetScore() {
        totalScore = 0;
    }
}
