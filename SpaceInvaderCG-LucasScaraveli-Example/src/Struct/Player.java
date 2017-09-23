/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Player extends Element {

    public boolean cd; // tempo de recarga para atirar
    public boolean block; // se ele esta bloqueado

    public Player(int tamX, int tamY, int posX, int posY, int velX, int velY, int sceX, int sceY, Type type, boolean alive, Team team) {
        super(tamX, tamY, posX, posY, velX, velY, sceX, sceY, type, alive, team);
        cd = false;
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.fillRect(posX, posY, tamX, tamY);
        }
    }

    @Override
    public void move(int dir, int esq) {
        posX += velX;

        if (dir > 0 && posX < sceX - tamX / 2 - 37) {
            velX = 2;
        } else if (esq > 0 && posX > 15) {
            velX = -2;
        } else {
            velX = 0;
        }
    }

    @Override
    public void atirar(Graphics g, boolean atirar, ArrayList<Element> list, int count) {
        if (atirar) {
            list.add(new Bullet(20, 15, posX, posY - 30, 0, -2, sceX, sceY, Element.Type.BULLET, true, Team.A));
        }
    }

}
