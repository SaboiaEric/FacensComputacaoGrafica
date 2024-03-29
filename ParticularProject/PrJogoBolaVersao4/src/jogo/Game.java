package jogo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
public class Game  {
    /*Set atributos*/
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
    private int fase = 3;
    private boolean reiniciar = false;
    

    public Game(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        player = new Raquete(largura / 2 - 40, altura - 40, 80, 20, Color.BLACK, this);
        player.setIncY(0);
        add(player);
        ultimoTiro = System.currentTimeMillis();
        placar = 0;
        danos = 0;
        
    }
    //Objetos inimigos
    public void initGame()
    {
        switch(fase){
            case 1:
                criaInimigos(5,false);
                break;
            case 2:
                criaInimigos(7,false);
                placar += 10;
                break;
            case 3:
                criaInimigos(10,false);
                placar += 20;
                break;
            case 4:
                criaInimigos(0,true);
                placar += 50;
                break;
        }
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
                //tentaCriarChefe(bg);
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
            this.reiniciar = reiniciar;
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
            
            if(fimDeJogo && this.reiniciar)
            {
                fimDeJogo = false;
                this.reiniciar = false;
                qtdOp = 0;
                fase = 1;
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
        bg.drawString("OP: " + qtdOp, 50,75);
        bg.drawString("FASE: " + fase, getLargura()-130,getAltura()-70);
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
                if(fase != 4){
                    fase++;
                    objetos.clear();
                    lixo.clear();
                    objetos.add(player);
                    this.initGame();
                }
                else{
                    fimDeJogo = true;
                    objetos.clear();
                    lixo.clear();
                    objetos.add(player);
                }
           }
           else
           {
                objetos.removeAll(lixo);
                lixo.clear();
           } 
        
    }

    private void fimDeJogo(Graphics bg) {
        desenharPlacar(bg);
        bg.setColor(Color.RED);
        String msg = "FIM DE JOGO - Tecla 'R' para reiniciar.";
        bg.drawString(msg,50,100);
    }

    private void tentaTiro() {
      if(tiro)
      {
        long tempoAtual = System.currentTimeMillis();
        if(tempoAtual >  ultimoTiro + 200)
        {
            ultimoTiro = tempoAtual;
            Tiro t = new Tiro(player.x + player.largura/2, player.y-10, 5,10,Color.RED, this);
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
                //Se o objeto colidiu com um inimigo, adiciona os objetos no lixo remove
                // a quantidade de inimigo e adiciona o valor no placar
                if(x.colisaoCom(y) && x instanceof Tiro)
                {
                    placar++;
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
    
    private void criaInimigos(int qtd, boolean chefe){
        if(!chefe){
            for(int i = 1; i < qtd+1; i++){
                add(new Bola(50+i*50, 50, 50, 50, Color.BLACK, this));
                qtdOp++;
            }
        }
        else{
            add(new Bola(150, 50, 50, 50, Color.RED, this));
            qtdOp++;
        }
    } 
}
