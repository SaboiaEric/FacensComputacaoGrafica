/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogo;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Glauco
 */
public class Raquete extends Base{
     
    
     public Raquete(int x, int y, int largura, int altura, Color color, Game game)
     {
         super(x, y, largura, altura, color, game);
     }

     @Override
     public void desenhar(Graphics g)
     {
          g.setColor(color);
          g.fillRect(x, y, largura, altura);
     }
     
     
     
}








