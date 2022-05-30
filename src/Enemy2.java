
import javax.swing.ImageIcon;


public class Enemy2 extends Enemy implements Runnable {
    
    public Enemy2(int x, int y, int ancho, int alto, PCanvas Panel) {
        super(x, y, ancho, alto, Panel);
        setImagen(new ImageIcon(getClass().getResource("img/enemy_ship2.png")).getImage());
        velocidadMov = 30;
    }

    @Override
    public void mover() {
        super.mover(); 
    }
    
    
}
