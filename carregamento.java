
package main;

import java.awt.Color;
import javax.swing.UIManager;


   
  public class carregamento extends javax.swing.JFrame implements Runnable {
    
    public carregamento() {
         // Define o tamanho da janela (ajuste de acordo com suas preferências)
        setSize(589, 550);

        // Centraliza a janela na tela
        setLocationRelativeTo(null);
        
        try {
            UIManager.put("nimbusOrange", new Color(57, 255, 20));
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }

            lbl_count.setText(i + " %");
            barrap.setValue(i);
        }

        dispose();
        new mainClass().setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        barrap = new javax.swing.JProgressBar();
        lbl_count = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);
        jPanel1.add(barrap);
        barrap.setBounds(190, 400, 220, 20);

        lbl_count.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LILITH_cARREGAMENTO.png"))); // NOI18N
        lbl_count.setText("jLabel1");
        jPanel1.add(lbl_count);
        lbl_count.setBounds(10, -10, 610, 550);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(carregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(carregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(carregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(carregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new carregamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barrap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_count;
    // End of variables declaration//GEN-END:variables
}