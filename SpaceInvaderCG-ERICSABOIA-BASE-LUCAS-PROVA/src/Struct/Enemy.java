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
public class Enemy extends Element {

    public Enemy(int tamX, int tamY, int posX, int posY, int velX, int velY, int sceX, int sceY, Type type, boolean alive, Team team) {
        super(tamX, tamY, posX, posY, velX, velY, sceX, sceY, type, alive, team);
    }

    @Override
    public void move(int dir, int esq) {
        posX += velX;
        posY += velY;
        
        if(posY < 0){
            velY *= -1;
        }
        
        if(posY > sceY - tamY){
            alive = false;
        }
        
        if (posX + tamX/2 > sceX  || posX < 0){
            velX *= -1;
            velY = 0;
        }
        
    }

    @Override
    public void draw(Graphics g) {
     //   if (alive) {
       g.setColor(Color.yellow);
       g.fillRect(posX, posY, tamX, tamY);
      //  }
    }

    @Override
    public void atirar(Graphics g, boolean ok, ArrayList<Element> list, int count) {
       
        if(!alive && tamX > 15){
            
            Enemy a = new Enemy(tamX/2,tamY/2,posX,posY,-1,-1,sceX,sceY,type,true,team);
            Enemy b = new Enemy(tamX/2,tamY/2,posX,posY,1,-1,sceX,sceY,type,true,team);
            Enemy c = new Enemy(tamX/2,tamY/2,posX,posY,-1,1,sceX,sceY,type,true,team);
            Enemy d = new Enemy(tamX/2,tamY/2,posX,posY,1,1,sceX,sceY,type,true,team);
            
            
            list.add(a);
            list.add(b);
            list.add(c);
            list.add(d);
        }
        
    }

}
