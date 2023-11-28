package main;

import Mongo.conectaMongo;
import javax.swing.JOptionPane;

/**
 *
 * @author 23162799
 */
public class mainClass extends javax.swing.JFrame {

    /**
     * Creates new form inicio
     */
    public mainClass() {
        initComponents();
        // Define o tamanho da janela (ajuste de acordo com suas preferências)
        setSize(589, 550);

        // Centraliza a janela na tela
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        USUARIO = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        SENHA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 153));
        getContentPane().setLayout(null);

        USUARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USUARIOActionPerformed(evt);
            }
        });
        getContentPane().add(USUARIO);
        USUARIO.setBounds(180, 220, 212, 30);

        jButton3.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        jButton3.setText("Score");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(410, 220, 110, 27);
        getContentPane().add(SENHA);
        SENHA.setBounds(180, 310, 212, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LILITH Adventure.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, -20, 580, 550);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(190, 380, 190, 50);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(200, 460, 170, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Define o fundo do botão jButton1 como transparente
        jButton1.setBackground(new java.awt.Color(0, 0, 0, 1));

        // Cria uma instância da classe conectaMongo chamada "conn"
        conectaMongo conn = new conectaMongo();

        // Chama o método findValuesUsuarioeSenha da instância "conn" para procurar valores no MongoDB
        conn.findValuesUsuarioeSenha(USUARIO.getText(), Integer.parseInt(SENHA.getText()));

        // Cria uma nova instância da classe Game chamada "oi"
        Game oi = new Game();

        // Fecha a janela atual (dispose) para iniciar a instância do jogo
        dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Define o fundo do botão jButton2 como transparente
        jButton2.setBackground(new java.awt.Color(0, 0, 0, 1));

        // Cria uma nova instância da classe CADASTRO e a torna visível
        new CADASTRO().setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed
    private void USUARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USUARIOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_USUARIOActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Solicita ao usuário que digite o nome do usuário usando um JOptionPane de entrada
        String nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuário:");

        try {
            // Verifica se o nome do usuário não é nulo e não está vazio
            if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
                // Cria uma instância da classe conectaMongo para realizar a conexão com o MongoDB
                conectaMongo conexao = new conectaMongo();

                // Obtém o score do usuário usando o nome do usuário fornecido
                int score = conexao.getScore(nomeUsuario);

                // Verifica se o score foi encontrado
                if (score != -1) {
                    // Exibe uma mensagem com o nome do usuário e o score obtido
                    JOptionPane.showMessageDialog(null, "Usuário: " + nomeUsuario + "\nScore: " + score);
                } else {
                    // Exibe uma mensagem indicando que o usuário não foi encontrado
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }
            } else {
                // Exibe uma mensagem indicando que o nome do usuário é inválido
                JOptionPane.showMessageDialog(null, "Nome do usuário inválido.");
            }
        } catch (Exception e) {
            e.printStackTrace();  // ou trate a exceção de maneira adequada
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField SENHA;
    private javax.swing.JTextField USUARIO;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
