
import javax.swing.ImageIcon;


public class BulletEnemy extends Sprite implements Runnable{
    
    PCanvas PLienzo;
    public boolean destruida;
    public boolean destruidaF;
    public int velocidadBala;
    public BulletEnemy(int x, int y, int ancho, int alto, PCanvas Panel) {
        super(x, y, ancho, alto);
        PLienzo=Panel;
        setImagen(new ImageIcon(getClass().getResource("img/enemyBullet.png")).getImage());
        new Thread(this).start();
        destruidaF=false;
        velocidadBala =25;
    }
    
    @Override
    public void mover() {
        setY(getY()+velocidadBala);
        rectangulo.setLocation(getX(), getY());
        pausa(100); 
        PLienzo.repaint();
    }
    
    public void pausa (int tiempo){
       try {
                Thread.sleep(tiempo);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Enemigo.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

    @Override
    public void destruir() {
        //metodo para remover balas y poner destruidaF (Destruida definitva) en true
        if(this.getY()>PLienzo.getHeight()){
           Enemy.balasEnem.remove(this);
           destruidaF=true;
        }else if(destruida){
            Enemy.balasEnem.remove(this);
            destruidaF=true;
        }
        
        
    }
    
    

    @Override
    public void run() {
        //Al estar destruida definita en true deja de funcional el ciclo while por lo que el hilo se acaba
        while(!destruidaF){
            mover();
            destruir();
        }
    }
    
    
    
    
}
