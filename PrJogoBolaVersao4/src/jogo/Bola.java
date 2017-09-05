/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogo;

import java.awt.Color;
import java.awt.Graphics;


public class Bola extends Base{
     
     public Bola(int x, int y, int largura, int altura, Color color, Game game)
     {
         super(x, y, largura, altura, color, game);
     }
     @Override
     public void mover() {
        this.x = x + incX;
        //this.y = y + incY;
        rect.x = x;
        rect.y = y;
    }
     @Override
     public boolean colisaoCom(Base b) {
            return true;
    }
     
     @Override
     public void desenhar(Graphics g)
     {
          g.setColor(color);
          g.fillOval(x, y, largura, altura);
     }
     
     
    
    

}









