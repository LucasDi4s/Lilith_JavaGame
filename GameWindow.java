package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

/**
 * Classe que representa a janela do jogo e lida com eventos de foco.
 */
public class GameWindow {

    private JFrame jframe;

    /**
     * Construtor da classe GameWindow.
     *
     * @param gamePanel O painel do jogo a ser adicionado à janela.
     */
    public GameWindow(GamePanel gamePanel) {
        // Cria uma nova instância de JFrame.
        jframe = new JFrame();

        // Configuração da janela.
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel); // Adiciona o painel do jogo à janela.

        // Define a janela para não ser redimensionável pelo usuário
        jframe.setResizable(false);

        // Faz com que a janela adote automaticamente o tamanho preferencial dos seus componentes
        // Isso é útil para garantir que todos os componentes sejam exibidos corretamente sem espaço extra
        jframe.pack();

        // Centraliza a janela na tela do computador
        // Definir a localização como null faz com que a janela seja colocada no centro da tela
        jframe.setLocationRelativeTo(null);

        // Torna a janela visível
        // Este comando deve ser chamado após a configuração da janela para que ela seja exibida na tela
        jframe.setVisible(true);

        // Adiciona um ouvinte de foco à janela para lidar com eventos de foco.
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                // Chama o método windowFocusLost() no jogo quando a janela perde o foco.
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Método não implementado, pode ser estendido conforme necessário.
            }
        });
    }
}
