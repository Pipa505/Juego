
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class FinalBoss extends Enemy implements Runnable{
    
    private PCanvas PLienzo;
 
    
    public FinalBoss(int x, int y, int ancho, int alto, PCanvas Panel) {
        super(x, y, ancho, alto, Panel);
        velocidadMov =50;
        setImagen(new ImageIcon(getClass().getResource("img/boss.png")).getImage());
    }

   


    
    
    
}
