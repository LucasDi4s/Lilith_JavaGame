package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.Keyboardsinputs;
import inputs.Mouseinputes;
import static  main.Gameclass.GAME_HEIGHT;
import static main.Gameclass.GAME_WIDTH;

public class GamePanel extends JPanel {

    private Mouseinputes mouseinputes;
    private Gameclass game;

    // Construtor da classe GamePanel
    public GamePanel(Gameclass game) {
        // Inicializa o gerenciador de entrada do mouse
        mouseinputes = new Mouseinputes(this);
        // Associa a instância do jogo recebida ao painel
        this.game = game;
        // Configura o tamanho preferencial do painel
        setPanelSize();
        // Adiciona ouvintes de eventos de teclado e mouse
        addKeyListener(new Keyboardsinputs(this));
        addMouseListener(new Mouseinputes(this));
        addMouseMotionListener(new Mouseinputes(this));
    }

    // Define o tamanho preferencial do painel
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    // Método chamado para atualizar o estado do jogo
    public void updateGame() {
        // Implementação da lógica de atualização do jogo
    }

    // Sobrescreve o método paintComponent para desenhar na tela
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Chama o método de renderização do jogo para desenhar os elementos
        game.render(g);
    }

    // Retorna a instância da classe Gameclass associada a este painel
    public Gameclass getGameclass() {
        return game;
    }
}
