import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 140822
 */
public class Frm20170821 extends javax.swing.JFrame implements Runnable{

    private boolean setaEsquerda;
    private boolean setaDireita;
    /**
     * Creates new form Frm20170821
     */
    public Frm20170821() {
        initComponents();
        //Cria e utiliza dois buffers para controle de leitura e controle de escrita
        createBufferStrategy(2);
        //passo a thread que contem o método run
        Thread t = new Thread(this);
        t.start(); // Inicia a thread      
    }

    @SuppressWarnings("unchecked")
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            setaEsquerda = true;
        }
        else{
            if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                setaDireita = true;
            }
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            setaEsquerda = false;
        }
        else{
            if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                setaDireita = false;
            }
        }
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm20170821.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm20170821.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm20170821.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm20170821.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm20170821().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
         while(true){
             //Exercicios20170807();
             Random r = new Random();
             boolean x = true,y = true;
             int x1 = r.nextInt(getWidth()-40);
             int y1 = r.nextInt(getHeight()-40);
             int x2 = 300;
             while(true){
                 Graphics g = getBufferStrategy().getDrawGraphics();
                 g.setColor(Color.WHITE);
                 g.fillRect(0, 0, getWidth(), getHeight());
                 g.setColor(Color.PINK);
                 g.fillOval(x1, y1, 40, 40);
                 g.fillRect(x2, getHeight()-50, 40, 15);
                 
                 g.drawString("Placar = " + x2, 20, 50);
                 //Região para controle de seta
                 x2 = ControleMovimentoRaquete(x2);
                 
                 if(x1 <= 0)
                     x = true;
                 else if(x1 >= getWidth()-40) 
                     x = false;
                 
                 if(y1 <= 0+25)
                     y = true;
                 else if(y1 >= getHeight()-40) 
                     y = false ;
                 
                 if(x)
                    x1++;
                 else
                     x1--;
                 if(y)
                    y1++;
                 else
                     y1--;                             
                 //y++;
                 try {
                     Thread.sleep(5);
                 } catch (InterruptedException ex) {
                     
                 }
                 g.dispose();
                 getBufferStrategy().show();
             }
        }
    }
    
    public int ControleMovimentoRaquete(int x2){
        if(setaDireita)
            return x2 +=3;
        else if(setaEsquerda)
            return x2 -=3;
        else
            return x2;            
    }
     
    public void Exercicios20170807(){
        //Coleta o buffer disponível e coloca o gráfico para permitir desenhar
            Graphics g = getBufferStrategy().getDrawGraphics();        
            
            //Exercicio 1 e 2
            
            int a = 100;
            int b = 100;
            int c = 100;
            int d = 100;
            
            for (int i = 5; i <= 200; i+=5) {
                g.drawRect(a - i , b -i, c +i*2 , d +i*2);
            }
                       
            //Exercicio 3
            
            int e = 200;
            int s = 400;
            int f = e;
            
            for(int i = 0; i <= 80; i+=10){
                g.drawLine(e, f, e, s);
                g.drawLine(e, s, s, s);
                g.drawLine(s, s, s, e);
                g.drawLine(s, e, e+10, e);
                g.drawLine(s, e, e+10, e);
                f = e;
                e += 10;
                s -= 10;
            }
            
            
            int q = 20;
            int tam = 100;
            //Exercicio 4
            for(int i = 1; i <9; i++)
            {
                g.drawRect(tam, tam*i+1*q, q, q);
            }
            g.drawRect(100, 100, q, q);
            g.drawRect(100, 100+q, 20, 20);
            g.drawRect(100, 100+q*2, 20, 20);
            g.drawRect(100+q, 100, 20, 20);
            g.dispose();
            getBufferStrategy().show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
