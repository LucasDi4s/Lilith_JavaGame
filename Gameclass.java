package main;

import entities.Player;
import java.awt.Graphics;

public class Gameclass implements Runnable {

    private GameWindow gamewindow;
    private GamePanel gamepanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE  = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int  GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int  GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // Construtor da classe Gameclass
    public Gameclass() {
        initClasses();

        // Inicializa o painel do jogo e a janela do jogo
        gamepanel = new GamePanel(this);
        gamewindow = new GameWindow(gamepanel);

        // Solicita o foco no painel do jogo para lidar com eventos de entrada
        gamepanel.requestFocus();

        // Inicia o loop principal do jogo
        startGameLoop();
    }

    // Inicia a thread do loop principal do jogo
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Atualiza a lógica do jogo
    public void update() {
        player.update();
    }

    // Renderiza os elementos do jogo na tela
    public void render(Graphics g) {
        player.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                update++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamepanel.repaint();
                deltaF--;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + update);
                frames = 0;
                update = 0;
            }
        }
    }

    // Inicializa as classes necessárias para o jogo
    private void initClasses() {
        player = new Player(200, 200);
    }

    // Retorna a instância do jogador
    public Player getPlayer() {
        return player;
    }

    void WindowFocusLost() {
        player.resetDirBooleans();
    }

   }
