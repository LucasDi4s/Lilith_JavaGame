package main;

import java.awt.Graphics;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import utilz.LoadSave;

public class Game implements Runnable {

    // Componentes principais do jogo
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    // Configurações de taxa de quadros (FPS) e atualizações por segundo (UPS)
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    // Estados do jogo (menu, jogando, etc.)
    private Playing playing;
    private Menu menu;

    // Gerenciador de pontuação compartilhado
    private static ScoreManager scoreManager;

    // Configurações de tamanho e escala do jogo
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // Construtor principal do jogo
    public Game() {
        // Inicializa as classes principais
        initClasses();

        // Cria o painel e a janela do jogo
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        // Inicializa o gerenciador de pontuação
        scoreManager = new ScoreManager();

        // Inicia o loop principal do jogo
        startGameLoop();
    }

    // Inicializa as instâncias principais do jogo
    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    // Obtém o gerenciador de pontuação do jogo
    public static ScoreManager getScoreManager() {
        return scoreManager;
    }

    // Inicia a thread do loop do jogo
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Método de atualização do jogo
    public void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    // Método de renderização do jogo
    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    // Implementação da interface Runnable para o loop do jogo
    @Override
    public void run() {
        // Configuração de tempo para cada quadro e atualização
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        // Variáveis para controle de tempo
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            // Cálculo do delta de tempo para atualização
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // Atualizações do jogo
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            // Renderização do jogo
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // Verificação de desempenho a cada segundo
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // Método chamado quando a janela perde o foco
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }

    // Métodos de acesso aos componentes principais do jogo
    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
