/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Game.Game;
import Struct.Element;
import Struct.Enemy;
import Struct.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class Frame extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Frame
     */
    boolean setaDireita;
    boolean setaEsquerda;
    boolean atirar;
    boolean fb = false;
    int cooldown = 0;

    public Frame() {
        initComponents();

        createBufferStrategy(2);

        Thread t = new Thread(this);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 800));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            setaEsquerda = true;
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            setaDireita = true;

        }

        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            atirar = true;
            if (cooldown > 80) {
                cooldown = 0;
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            setaEsquerda = false;
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            setaDireita = false;
        }

        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            atirar = false;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyTyped

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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    @Override
    public void run() {

        Random rdm = new Random();
        int countFire = 0;

        ArrayList<Element> elements = new ArrayList<Element>();

       /* Player a = new Player(55, 15, 80, 550, 0, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.A);
        Enemy b = new Enemy(30, 30, 25, 35, 1, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.B);
        Enemy c = new Enemy(30, 30, 100, 35, 1, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.B);
        Enemy h = new Enemy(30, 30, 175, 35, 1, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.B);
        Enemy q = new Enemy(30, 30, 35, 95, -1, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.B);
        Enemy e = new Enemy(30, 30, 200, 95, -1, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.B);
        Enemy w = new Enemy(30, 30, 450, 95, -1, 0, getWidth(), getHeight(), Element.Type.PERSON, true, Element.Team.B);
       
        elements.add(a);
        elements.add(b);
        elements.add(c);
        elements.add(h);
        elements.add(w);
        elements.add(q);
        elements.add(e);*/
       
       Game x = new Game();
       
       int nivel = 1;
       int maxFire;
          
       maxFire = x.createLevel(getWidth(), getHeight(), nivel);

        while (/*qntBolas(elements) > 0*/true) {
            
            
            if(x.acabou()){
                maxFire = x.createLevel(getWidth(), getHeight(), nivel++);
            }

            if (countFire >= maxFire) {
                countFire = 0;
            }

            Graphics g = getBufferStrategy().getDrawGraphics();
            
            
            
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            x.drawScores(g);

            g.setColor(Color.RED);
            
            if(maxFire == 0)
                return;

    /*        if (!fb) {
                g.drawString("VAI COMEÇAR!!!!", getHeight() / 2, getWidth() / 2);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {

                }
                fb = true;
            }*/
            // Font b = new Font("Arial", 1, 25);
            //  g.setFont(b);
            //    g.drawString(Integer.toString(qntBolas(elements)), 30, 60);

            ArrayList<Element> novos = new ArrayList<Element>();

           // for (Element u : elements) {

               // u.refreshScenario(getWidth(), getHeight());
               
                x.render(g, setaDireita ? 1 : -1, setaEsquerda ? 1 : -1, atirar,countFire );

              //  u.draw(g);

              //  u.move(setaDireita ? 1 : -1, setaEsquerda ? 1 : -1);
              //  u.atirar(g, atirar, novos, countFire);
                if (atirar) {
                    atirar = false;
                }
                //u.collision(elements);
          //  }

            elements.addAll(novos);

            /*  txt_qntBolas.setText( Integer.toString(qntBolas(elements)));
            txt_qntBolas.paintAll(g);*/
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {

            }

            countFire++;
            cooldown++;

            g.dispose();
            getBufferStrategy().show();
        }

        //  run();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
