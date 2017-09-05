/*
 * FrmJogo.java
 *
 * Created on 7 de Agosto de 2008, 09:51
 */
package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Glauco
 */
public class FrmJogo extends javax.swing.JFrame implements Runnable {

    private boolean esquerdo = false;
    private boolean direito = false;
    private boolean reiniciar = false;
    private boolean tiro;
    
    /**
     * Creates new form FrmJogo
     */
    public FrmJogo() {
        initComponents();
        //Constroi um buffer duplo
        createBufferStrategy(2);
        //Constroi uma Thread
        Thread t = new Thread(this);
        //Start a Thread
        t.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) tiro = true;
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) esquerdo = true;
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) direito = true;
        if (evt.getKeyCode() == KeyEvent.VK_R) reiniciar = true;           

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) tiro = false;
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) esquerdo = false;
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) direito = false;
        if(evt.getKeyCode() == KeyEvent.VK_R) reiniciar = false;
    }//GEN-LAST:event_formKeyReleased

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJogo().setVisible(true);

            }
        });
    }

    public void run() {
        BufferStrategy buffer = getBufferStrategy();
        Graphics bg;
        Game g = new Game(getWidth(), getHeight());
        g.initGame();
        while (true) {
            //Aloca o Graphics
            bg = buffer.getDrawGraphics();
            bg.setFont(new Font("Dialog",Font.ITALIC,25));
            g.upDate(bg);
            g.setPlayerActions(direito, esquerdo, reiniciar, tiro);
            //Libera o Graphics
            bg.dispose();
            //Mostra o desenho
            buffer.show();
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {

            }
        }

    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
