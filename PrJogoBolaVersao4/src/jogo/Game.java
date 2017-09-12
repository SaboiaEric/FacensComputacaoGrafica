package jogo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
public class Game  {
    private ArrayList<Base> objetos = new ArrayList<Base>();
    private ArrayList <Base> lixo = new ArrayList<Base>();
    private Base player;
    private int placar=0;
    private int largura;
    private int altura;
    private int danos=0;
    private boolean tiro;
    private boolean fimDeJogo= false;
    private long ultimoTiro;
    private int qtdOp = 0;

    public Game(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        player = new Raquete(largura / 2 - 40, altura - 40, 80, 20, Color.BLACK, this);
        player.setIncY(0);
        add(player);
        ultimoTiro = System.currentTimeMillis();
    }
    //Objetos inimigos
    public void initGame()
    {
        add(new Bola(50, 50, 50, 50, Color.BLACK, this));
        qtdOp++;
        add(new Bola(100, 50, 50, 50, Color.BLACK, this));
        qtdOp++;
        add(new Bola(150, 50, 50, 50, Color.BLACK, this));
        qtdOp++;
        placar = 0;
        danos = 0;
    }

    public Base getPlayer() {
        return player;
    }

    public void setPlayer(Base player) {
        this.player = player;
    }
    
    public void upDate(Graphics bg)
    {
            
            limpaTela(bg);
            if(fimDeJogo)
            {
                fimDeJogo(bg);
            }
            else
            {
                tentaCriarChefe(bg);
                moverTodos();
                desenharPlacar(bg);
                desenharTodos(bg);
                tentaTiro();
                verificaTiro();
                verificarColisaoComPlayer();
                verificarDano();
                verificarColisaoComGame();
                verificarFim();
                
            }
        
    }
    
    
    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    
    public void add(Base b)
    {
        objetos.add(b);
    }

    public void setPlayerActions(boolean direito,
                                boolean esquerdo,
                                boolean reiniciar, 
                                boolean tiro) {
            
            if (direito && player.getX() < largura - player.getLargura()) {
                player.setIncX(1);
            } else if (esquerdo && player.getX() > 0) {
                player.setIncX(-1);
            } else {
                player.setIncX(0);
            }
            
            if(qtdOp > 0)
                this.tiro = tiro;
            else
                this.tiro = false;
            
            if(fimDeJogo && reiniciar)
            {
                fimDeJogo = false;
                reiniciar = false;
                qtdOp = 0;
                initGame();
            }
    }

    private void moverTodos() {
        for (Base b : objetos) {
                b.mover();
            }
    }

    private void desenharTodos(Graphics bg) {
     for (Base b : objetos) {
                b.desenhar(bg);
            }
    }

    private void verificarColisaoComPlayer() {
            for(Base b: objetos)
            {
                if(player.colisaoCom(b))
                {
                    b.incY = -1;
                    placar++;
                }
            }
    }

    private void verificarColisaoComGame() {

        for(Base b: objetos)
        {
            if(b.getX() < 0){
                for (Base c: objetos) {
                    if(c instanceof Bola){
                        c.setIncX(1);
                        //c.setIncY(10); 
                        c.setY(c.getY()+20);
                    }
                }
            }
            if(b.getX() > getLargura() - b.largura){
                for (Base c: objetos) {
                    if(c instanceof Bola){
                        c.setIncX(-1);
                        //c.setIncY(10); 
                        c.setY(c.getY()+20);
                    }
                }              
            }
            
            //if(b.getY() < 0)
              //      b.setIncY(10);  
            
            if(b.getX() > largura - b.getLargura())
                b.setIncX(-1);
            
        }
    }

    private void desenharPlacar(Graphics bg) {
        bg.setColor(Color.BLACK);
        bg.drawString("Placar: " + placar + " Danos: " + danos,50,50);
        bg.drawString("OP: " + qtdOp, 50,100);
    }

    private void limpaTela(Graphics bg) {
        
        bg.setColor(Color.GREEN);
        bg.fillRect(0, 0, largura, altura);
            
    }

    private void verificarDano() {
        for(Base b: objetos)
        {
           if(b.getY() > altura)
           {
               danos ++;
               lixo.add(b);
               
           }    
        }   
    }

    
    private void verificarFim() {
           if(objetos.size() == 1 || lixo.contains(player))
           {
               fimDeJogo = true;
                objetos.clear();
                lixo.clear();
                objetos.add(player);
                
           }
           else
           {
                objetos.removeAll(lixo);
                lixo.clear();
           } 
        
    }

    private void fimDeJogo(Graphics bg) {
        
        bg.setColor(Color.BLACK);
        String msg = "FIM DE JOGO - Tecla 'R' para reiniciar.";
        bg.drawString(msg,20,100);
    }

    private void tentaTiro() {
      if(tiro)
      {
        long tempoAtual = System.currentTimeMillis();
        if(tempoAtual >  ultimoTiro + 200)
        {
            ultimoTiro = tempoAtual;
            Tiro t = new Tiro(player.x + player.largura/2, player.y-20, 5,10,Color.RED, this);
            t.incX=0;
            t.incY=-1;
            objetos.add(t);
        }
      } 
    }

    private void verificaTiro() {
        for(Base x: objetos){
            for(Base y: objetos)
            {
                if(x.colisaoCom(y) && x instanceof Tiro)
                {
                    lixo.add(x);
                    lixo.add(y);
                    --qtdOp;
                }
                if(y instanceof Tiro && y.y < 0){
                    lixo.add(y);
                    
                }
            }
        }
        
    }

    //Fase do chefão
    private void tentaCriarChefe(Graphics bg) {
      if(placar == 3){
          Chefe c = new Chefe(40,40,50,50,Color.PINK,this);
          objetos.add(c);
          c.incX=1;
          c.incY=-1;
          placar ++;
      }
    }
    
    
    
    
    
}
