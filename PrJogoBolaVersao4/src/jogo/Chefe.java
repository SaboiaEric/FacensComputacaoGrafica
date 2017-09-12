/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Abutua
 */
public class Chefe extends Base {

    public Chefe(int x, int y, int largura, int altura, Color color, Game game) {
        super(x, y, largura, altura, color, game);
    }

    @Override
    public void desenhar(Graphics g) {
         g.setColor(color);
         g.fill3DRect(x, x, largura, altura, true);
    }
    
    @Override
    public void mover(){
        incX = (int) (Math.random() * 10) - 5;
        incY= 1;
        x = x + incX;
        y = y + incY;
        rect.x = x;
        rect.y = y;
    }
    
}
