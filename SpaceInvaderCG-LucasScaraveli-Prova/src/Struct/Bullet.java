/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Bullet extends Element {

    public Bullet(int tamX, int tamY, int posX, int posY, int velX, int velY, int sceX, int sceY, Type type, boolean alive, Team team) {
        super(tamX, tamY, posX, posY, velX, velY, sceX, sceY, type, alive, team);
    }
    

    @Override
    public void move(int dir, int esq) {
        posY += velY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
       // if(alive)
        g.fillRect(posX, posY, tamX, tamY);
    }

    @Override
    public void atirar(Graphics g, boolean ok, ArrayList<Element> list, int count) {
       
    }
    
}
