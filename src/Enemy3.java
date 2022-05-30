
import javax.swing.ImageIcon;


public class Enemy3 extends Enemy implements Runnable {
    
    public Enemy3(int x, int y, int ancho, int alto, PCanvas Panel) {
        super(x, y, ancho, alto, Panel);
        setImagen(new ImageIcon(getClass().getResource("img/enemy_ship3.png")).getImage());
        velocidadMov = 40;
    }
    
}
