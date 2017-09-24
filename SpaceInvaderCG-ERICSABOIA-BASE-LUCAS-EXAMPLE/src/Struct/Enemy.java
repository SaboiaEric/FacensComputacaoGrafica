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

    public boolean donts;

    public Enemy(int tamX, int tamY, int posX, int posY, int velX, int velY, int sceX, int sceY, Type type, boolean alive, Team team) {
        super(tamX, tamY, posX, posY, velX, velY, sceX, sceY, type, alive, team);
        donts = false;
    }

    @Override
    public void move(int dir, int esq) {
        posX += velX;

        if (posX > sceX - 34) {
            velX = -1;
            posY += 50;
            donts = true;
        } else if (posX < 15) {
            velX = +1;
            posY += 50;
            donts = true;
        }
        if (posY > sceY) {
            alive = false;
            donts = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.yellow);
            g.fillRect(posX, posY, tamX, tamY);
            
        }
    }

    @Override
    public void atirar(Graphics g, boolean ok, ArrayList<Element> list, int count) {
        if (count == 0 && alive) {
            list.add(new Bullet(15, 15, posX, posY + 30, 0, 2, sceX, sceY, Element.Type.BULLET, true, Team.B));
        }
    }

}
