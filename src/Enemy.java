
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class Enemy extends Sprite implements Runnable {
    private final PCanvas PLienzo;
    public int dir;
    public boolean destruido;
    public int velocidadMov = 25;
    public static ArrayList<BulletEnemy> balasEnem;

    public Enemy(int x, int y, int ancho, int alto, PCanvas Panel) {
        super(x, y, ancho, alto);
        setImagen(new ImageIcon(getClass().getResource("img/enemy_ship1.png")).getImage());
        PLienzo = Panel;
        dir = 1;
        new Thread(this).start();
        destruido = false;
        balasEnem = new ArrayList<BulletEnemy>();
    }

    @Override
    public void mover() {
        setX(getX() + velocidadMov * dir);
        rectangulo.setLocation(getX(), getY());
        PLienzo.repaint();
        pausa(100);
    }

    @Override
    public void Limite() {
        //al llegar al limite cambia la direccion de los enemigos
        if (getX() > PLienzo.getWidth() - 130) {
            dir = -1;
            setY(this.getY() + 60);
        } else if (getX() < PLienzo.getX() + 25) {
            dir = 1;
            setY(this.getY() + 60);
        }
    }


    public void pausa(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            //Logger.getLogger(Enemigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        //Los movimientos y limites de las naves estan dentro de un hilo que hace que se muevan solas, estas estan dentro de un while y cuando
        //este se marque true deja de hacer esos movimientos y se muere el hilo
        while (!destruido) {
            mover();
            Limite();
        }
    }


}
