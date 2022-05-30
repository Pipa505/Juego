
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player extends Sprite {

    PCanvas PLienzo;
    public static ArrayList<Bullet> balas;
    public Player(int x, int y, int ancho, int alto,PCanvas panel) {
        super(x, y, ancho, alto);
        PLienzo=panel;
        setImagen(new ImageIcon(getClass().getResource("img/ship.PNG")).getImage());
        balas=new ArrayList<>();

    }

    @Override
    public void mover() {
        //Permite al jugador moverse mediante teclas
        int direccion = FVentana.tecla;
        if (direccion == 39) {
            setX(getX() + 20);
        }
        if (direccion == 37) {
            setX(getX() - 20);
        }
        rectangulo.setLocation(getX(), getY());
    }

    @Override
    public void Limite() {
        //No deja que el jugador se salga de los bordes
        if(this.getX()<PLienzo.getX()+25){
            this.setX(PLienzo.getX()+25);
        }else if(this.getX()>PLienzo.getWidth()-130){
            this.setX(PLienzo.getWidth()-120);
        }
    }
    
    

    @Override
    public void shoot() {
       int shooting = FVentana.tecla;
       //Metodo disparar del jugador, solo dispara sin no hay balas o si la ultima bala disparada esta a mas de 150 pixeles de lejos
       if(balas.isEmpty()||balas.get(balas.size()-1).getY()<this.getY()-150){
       if(shooting==38){
           //ya pasando la condicion crea el objeto y lo agrega al arreglo
           Bullet bala = new Bullet(this.getX()+33, this.getY(), 15, 15,PLienzo);
           balas.add(bala);
       }
       }
    }
    

}
