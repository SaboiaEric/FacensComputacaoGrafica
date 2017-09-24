/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Struct.Element;
import Struct.Enemy;
import Struct.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Game {
    
    public ArrayList<Element> elementos;
    public int nivel;
    public boolean esquerda;
    public boolean direita;
    
    public int createLevel(int w,int h,int n){
        nivel = n;
        elementos = new ArrayList<Element>();
        
        Player a = new Player(82, 15, 80, 750, 0, 0, w,h, Element.Type.PERSON, true, Element.Team.A);
        elementos.add(a);
        
        Enemy gigante = new Enemy(120,120,w/2,h/2 - 300,1,0,w,h,Element.Type.PERSON, true, Element.Team.B);
        elementos.add(gigante);
       
        // speed do tiro
        return 280;
        
    }
    
    public void render(Graphics g, int dir, int esq, boolean atirar, int count){
        
        ArrayList<Element> n = new ArrayList<Element>();
        
        for (Element u : elementos){
        u.collision(elementos);
        }
        
        for (Element u : elementos){
            
            u.atirar(g, atirar, n, count);
            u.move(dir,esq);
            u.draw(g);
        }
        
        elementos = new ArrayList<Element>(refreshList(elementos,n));
        
    }
    
    public ArrayList<Element> refreshList(ArrayList<Element> e, ArrayList<Element> n) {
        
        ArrayList<Element> k = new ArrayList<Element>();
        
        for (Element u : e){
            
            //neste contexto o player nunca morre
            if (u.alive || (u.type == Element.Type.PERSON && u.team == Element.Team.A)){
                k.add(u);
            }
            
        }
        k.addAll(n);
        
        return k;
    }
    
    public boolean acabou(){
        
        
           for (Element u : elementos){
               
            if(u.type == Element.Type.PERSON && u.team == Element.Team.A && !u.alive){
                return false;
            }
            
            if (u.alive && u.type == Element.Type.PERSON && u.team == Element.Team.B){
                
                return false;
            }
            
        }
           
           return true;
        
    }
    
    public void drawScores(Graphics g){
            g.setColor(Color.BLUE);
            Font b = new Font("Arial", 1, 25);
            g.setFont(b);
            g.drawString("Level: " + nivelAtual(), 15, 50);
            g.drawString("Inimigos: " + inimigosVivos(), 15, 75);
    }
    
    private String inimigosVivos(){
        
        int count = 0;
        
        for (Element u : elementos){
            
            if(u.type == Element.Type.PERSON && 
                    u.team == Element.Team.B &&
                    u.alive){
                count++;
            }
            
        }
        
        return String.valueOf(count) ;
    }
    
    public String nivelAtual(){
        return nivel != 5 ? String.valueOf(nivel-1) : "BOSS";
    }
    
}
