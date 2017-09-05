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
public class Tiro extends Base {

    public Tiro(int x, int y, int largura, int altura, Color color, Game game)
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
