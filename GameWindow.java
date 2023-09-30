package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow {

    private JFrame jframe;

    // Construtor da classe GameWindow
    public GameWindow(){
        
    }
    public GameWindow(GamePanel gamepanel) {
        // Cria uma nova instância do JFrame
        jframe = new JFrame();

        // Define o comportamento padrão ao fechar a janela
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Adiciona o painel do jogo ao JFrame
        jframe.add(gamepanel);
        // Centraliza a janela na tela
        jframe.setLocationRelativeTo(null);
        // Impede que a janela seja redimensionada pelo usuário
        jframe.setResizable(false);
        // Ajusta o tamanho da janela com base no conteúdo do painel
        jframe.pack();
        // Torna a janela visível
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
           
            @Override
            public void windowGainedFocus(WindowEvent e) {
              gamepanel.getGameclass().WindowFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
               
            }

        });

    }
}
