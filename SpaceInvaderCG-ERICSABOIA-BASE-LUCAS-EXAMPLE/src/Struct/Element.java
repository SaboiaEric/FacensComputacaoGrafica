/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public abstract class Element {

    public enum Type {
        PERSON, BULLET;
    }

    public enum Team {
        A, B;
    }

    public int tamX; // Tamanho do elemento em x
    public int tamY; // Tamanho do elemento em y
    public int posX; // Posição em X
    public int posY; // Posição em Y
    public int velX; // Velocidade em X
    public int velY; // Velocidade em Y
    public int sceX; // Tamanho do cenário atual em X
    public int sceY; // Tamanho do cenário atual em Y
    public Type type; // Tipo do elemento (Pessoa,Tiro)
    public Team team; // Time do elemento (A,B)
    public boolean alive; // Estado do elemento (vivo ou morto)

    public Element(int tamX, int tamY, int posX, int posY, int velX, int velY, int sceX, int sceY, Type type, boolean alive, Team team) {
        this.tamX = tamX;
        this.tamY = tamY;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.sceX = sceX;
        this.sceY = sceY;
        this.type = type;
        this.alive = alive;
        this.team = team;
    }
    
    // refreshScenario atualiza os valores de cenário que
    // o objeto se situa.
    public void refreshScenario(int x, int y) {
        sceX = x;
        sceY = y;

        if (!alive) {
            posX = 0;
            posY = 0;
            tamX = 0;
            tamY = 0;
            velX = 0;
            velY = 0;
        }
    }
    
    // Collision implementa a colisão padrão do elemento,
    // ele a verifica comparando com todos os elementos
    // presente na tela. Quando o mesmo se colide, este objeto
    // será alterado.
    public void collision(ArrayList<Element> elements) {

        Rectangle rctA = new Rectangle(posX, posY, tamX, tamY);

        for (Element t : elements) {
            
            
            
            
            // Caso for do mesmo time ou o mesmo tipo, não há colisão
            if (t.type == this.type || t.team == this.team) {
                continue;
            }
            
            /*if(t.type.PERSON == type.PERSON && this.type == Type.BULLET){
                t.alive = false;
            }*/

            Rectangle rctB = new Rectangle(t.posX, t.posY, t.tamX, t.tamY);
            
            // Se ele colidir, ele morre!
            if (rctB.intersects(rctA)) {
                alive = false;
            }

        }

    }
    
    // move implementa a movimentação do elemento
    public abstract void move(int dir, int esq);
    
    // draw desenha o elemento
    public abstract void draw(Graphics g);
    
    // atiar implementa o metodo de atirar
    public abstract void atirar(Graphics g, boolean ok, ArrayList<Element> list, int contTir);

}
