
import javax.swing.ImageIcon;


public class Bullet extends Sprite implements Runnable{
    PCanvas PLienzo;
    public boolean destruida;
    public boolean destruidaF;
    public Bullet(int x, int y, int ancho, int alto,PCanvas Panel) {
        super(x, y, ancho, alto);
        PLienzo=Panel;
        setImagen(new ImageIcon(getClass().getResource("img/bullet.png")).getImage());
        new Thread(this).start();
        destruidaF=false;
    }

    @Override
    public void mover() {
        //Esto es para que la bala del jugador avance 
        setY(getY()-35);
        rectangulo.setLocation(getX(), getY());
        PLienzo.repaint();
        pausa(100); 
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
        //Destruye las balas que se van a traves del panel 
        
        if(this.getY()<PLienzo.getY()){
            Player.balas.remove(this);
            destruidaF=true;
        }else if(destruida){
            Player.balas.remove(this);
            destruidaF=true;
        }
    }
    
    
       
    @Override
    public void run() {
        while(!destruidaF){
            mover();
            destruir();
        }        
    }
    
    
}
