
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class Sprite {
    private int x, y, ancho, alto;
    private Image imagen;
    Rectangle rectangulo;

    public Sprite(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        rectangulo = new Rectangle(x, y, ancho, alto);
    }

    public void mover() {
    }

    public void shoot() {
    }

    public void destruir() {
    }

    public void Limite() {

    }

    public void laschivas() {

    }

    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        }
    }

    public boolean detectarColision(Sprite objeto) {
        return rectangulo.intersects(objeto.rectangulo);
    }

    public void chocar() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
