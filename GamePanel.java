package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import main.ScoreManager;

public class GamePanel extends JPanel {

    // Inputs do mouse para interação com o painel do jogo
    private MouseInputs mouseInputs;

    // Referência para a instância principal do jogo
    private Game game;

    // Texto base para a pontuação exibida no painel
    private String scoreText = "Score: ";

    // Referência para o gerenciador de pontuação do jogo
    private ScoreManager scoreManager;

    // Construtor do painel do jogo
    public GamePanel(Game game) {
        // Configuração dos inputs do mouse
        mouseInputs = new MouseInputs(this);

        // Associa a instância principal do jogo
        this.game = game;

        // Obtém o gerenciador de pontuação do jogo
        this.scoreManager = game.getScoreManager();

        // Define o tamanho preferencial do painel
        setPanelSize();

        // Adiciona ouvintes de eventos de teclado e mouse ao painel
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Define o tamanho preferencial do painel
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    // Atualiza o jogo e repinta o painel
    public void updateGame() {
        repaint();
    }

    // Sobrescreve o método paintComponent para renderizar o jogo
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Chama o método de renderização do jogo
        game.render(g);

        // Verifica se scoreManager não é null antes de chamar getTotalScore().
        if (game.getScoreManager() != null) {
            // Desenha a pontuação
            g.drawString("Score: " + game.getScoreManager().getTotalScore(), 10, 20);
        } else {
            // Trata o caso em que scoreManager é null.
            g.drawString("Score: N/A", 10, 20);
        }
    }

    // Obtém a instância do jogo associada ao painel
    public Game getGame() {
        return game;
    }
}
