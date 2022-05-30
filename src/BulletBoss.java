
import javax.swing.ImageIcon;


public class BulletBoss extends BulletEnemy implements Runnable{
    
    public BulletBoss(int x, int y, int ancho, int alto, PCanvas Panel) {
        super(x, y, ancho, alto, Panel);
        setImagen(new ImageIcon(getClass().getResource("img/Destroy.png")).getImage());
        velocidadBala=35;
    }
    
    
}
