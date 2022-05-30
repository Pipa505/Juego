
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author miguel
 */
public class PCanvas extends javax.swing.JPanel {

    Image background = new ImageIcon(getClass().getResource("img/fondaco.jpg")).getImage();
    FVentana ventana;
    int puntos = 0;
    int vidas;
    int posX = 0;
    int nivel = 1;
    int vidaBoss = 300;
    public static ArrayList<BulletEnemy> balasBoss;

    public PCanvas(FVentana ventana) {
        this.ventana = ventana;
        initComponents();
        vidas = 100;
        balasBoss = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        g.drawImage(background, 0, 0, null);

        ventana.player.dibujar(g);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Puntos: " + puntos, 20, 40);
        g.drawString("Vidas: " + vidas, this.getWidth() - 240, 40);

        VidaBoss(g);
        Niveles();
        Perder();

        detectarColision(FVentana.enemigos);
        detectarColisionBoss();

        DisparosEnemigos(FVentana.enemigos, Enemy.balasEnem);
        DisparosBoss(FVentana.jefesFinales, balasBoss);

        ColisionBalasEnemigas(Enemy.balasEnem);
        ColisionBalasEnemigas(balasBoss);

        colisionChoque(FVentana.enemigos, false);
        colisionChoque(FVentana.jefesFinales, true);

        Pintar(FVentana.enemigos, g);
        Pintar(FVentana.jefesFinales, g);
        PintarBala(Player.balas, g);
        PintarBalaEnemiga(Enemy.balasEnem, g);
        PintarBalaEnemiga(balasBoss, g);

        DestruirJefe();

    }

    public void DestruirJefe() {
        //destruye al jefe final cuando tiene 0 de vida
        if (vidaBoss <= 0) {
            try {
                FVentana.jefesFinales.get(FVentana.jefesFinales.size() - 1).destruir();
                FVentana.jefesFinales.remove(0);
                //JOptionPane.showMessageDialog(null,"!HAS PERDIDO!");

            } catch (Exception e) {
            }

        }
    }

    public void Perder() {
        if (vidas < 0) {
            System.exit(0);
        } else if (vidaBoss < 0) {
            System.exit(0);
        }

    }

    public void VidaBoss(Graphics g) {

        //Esto es para que dibuje la barra de vida del jefe Final
        if (!FVentana.jefesFinales.isEmpty() && vidaBoss > 0) {
            g.setColor(Color.yellow);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Vida Jefe FINAL", (this.getWidth() / 2) - 110, 80);
            g.setColor(Color.DARK_GRAY);
            g.fill3DRect((this.getWidth() / 2) - 150, 100, 300, 30, true);
            g.setColor(Color.RED);
            g.fill3DRect((this.getWidth() / 2) - 150, 100, vidaBoss, 30, true);
        }
    }

    public void Pintar(ArrayList<Sprite> lista, Graphics g) {
        //Esto es para que pinte en el PCancavas a los enemigos
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).dibujar(g);
        }
    }

    public void PintarBala(ArrayList<Bullet> lista, Graphics g) {
        //Esto es para que pinte en el PCanvas a las balas del jugador
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).dibujar(g);
        }
    }

    public void PintarBalaEnemiga(ArrayList<BulletEnemy> lista, Graphics g) {
        //Esto es para que pinte balas enemigas en el PCanvas
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).dibujar(g);
        }
    }

    public void detectarColisionBoss() {
        //Esto es para que detecte colisiones de las balas con el jefeFinal
        if (!FVentana.jefesFinales.isEmpty()) {
            for (int i = 0; i < Player.balas.size(); i++) {
                try {
                    if (Player.balas.get(i).detectarColision(FVentana.jefesFinales.get(FVentana.jefesFinales.size() - 1))) {
                        vidaBoss = vidaBoss - 50;
                        Player.balas.get(i).destruida = true;
                        Player.balas.remove(i);
                        puntos++;
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void detectarColision(ArrayList<Sprite> lista) {
        //Este es para que detecte colision de las balas del jugador con el enemigo
        for (int i = 0; i < lista.size(); i++) {
            Sprite enemyTemp = lista.get(i);
            for (int j = 0; j < Player.balas.size(); j++) {
                try {
                    if (Player.balas.get(j).detectarColision(enemyTemp)) {
                        lista.get(i).destruir();
                        lista.remove(i);
                        Player.balas.get(j).destruida = true;
                        Player.balas.remove(j);
                        puntos++;
                    }
                } catch (Exception e) {
                }
            }
        }

    }

    public void DisparosEnemigos(ArrayList<Sprite> tipoEnemigo, ArrayList<BulletEnemy> tipoBala) {

        //Esto es para que disparen los enemigos
        for (int i = 0; i < tipoEnemigo.size(); i++) {

            //Estos dos int redondean la posicion del enemigo y jugador en cienes
            int playerRounded = ((ventana.player.getX() + 99) / 100) * 100;
            int enemyRounded = ((tipoEnemigo.get(i).getX() + 99) / 100) * 100;

            //Si estan en la x disparan (por ejemplo 200 y 200)
            if (playerRounded == enemyRounded) {
                if (tipoBala.isEmpty() || tipoBala.get(tipoBala.size() - 1).getY() > tipoEnemigo.get(i).getY() + 120) {
                    BulletEnemy balaEnemiga = new BulletEnemy(tipoEnemigo.get(i).getX() + (tipoEnemigo.get(i).getAncho() / 2), tipoEnemigo.get(i).getY(), 30, 60, this);
                    tipoBala.add(balaEnemiga);
                }
            }
        }
    }

    public void DisparosBoss(ArrayList<Sprite> tipoEnemigo, ArrayList<BulletEnemy> tipoBala) {
        //Esto es para que dispare el jefe final
        for (int i = 0; i < tipoEnemigo.size(); i++) {

            //funciona igual que la sde los enemigos solo que dispara otra clase de Bala
            int playerRounded = ((ventana.player.getX() + 99) / 100) * 100;
            int enemyRounded = ((tipoEnemigo.get(i).getX() + 99) / 100) * 100;

            if (playerRounded == enemyRounded) {
                if (tipoBala.isEmpty() || tipoBala.get(tipoBala.size() - 1).getY() > tipoEnemigo.get(i).getY() + 120) {
                    BulletBoss balaEnemiga = new BulletBoss(tipoEnemigo.get(i).getX() + (tipoEnemigo.get(i).getAncho() / 2), tipoEnemigo.get(i).getY(), 50, 80, this);
                    tipoBala.add(balaEnemiga);
                }
            }
        }
    }

    public void ColisionBalasEnemigas(ArrayList<BulletEnemy> lista) {

        //Verifica la colision de las balas enemigas con el jugador y esta dentro de un trycatch porque puede dar errores
        for (int i = 0; i < lista.size(); i++) {
            try {
                if (lista.get(i).detectarColision(ventana.player)) {
                    lista.get(i).destruida = true;
                    lista.remove(i);
                    vidas--;
                }
            } catch (Exception e) {
            }
        }
    }

    public void colisionChoque(ArrayList<Sprite> lista, boolean FinalBoss) {
        //La funcion de que si chocan las naves enemigas con el jugador se destruyan, contiene una boolean que lo que hace es que si es true
        //significa que es un final boss entonces al chocar pierde directamente el jugador
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).detectarColision(ventana.player)) {
                lista.get(i).destruir();
                lista.remove(i);
                if (FinalBoss) {
                    vidas = 0;
                    break;
                }
                vidas--;
                puntos++;
            }
        }
    }

    public void Niveles() {
        //la funcion  de los niveles, solo aumenta el nivel y va agregando mas enemigos
        if (puntos == 5 && nivel == 1) {
            nivel++;
            for (int i = 0; i < 5; i++) {
                posX += 100;
                ventana.enemigos.add(new Enemy(posX, 20, 80, 70, this));
            }
            posX = 0;
        } else if (puntos == 10 && nivel == 2) {
            nivel++;
            for (int i = 0; i < 5; i++) {
                posX += 100;
                ventana.enemigos.add(new Enemy2(posX, 20, 80, 70, this));
            }
            posX = 0;
        } else if (puntos == 15 && nivel == 3) {
            nivel++;
            for (int i = 0; i < 6; i++) {
                posX += 100;
                ventana.enemigos.add(new Enemy2(posX, 20, 80, 70, this));
            }
            posX = 0;
        } else if (puntos == 21 && nivel == 4) {
            nivel++;
            for (int i = 0; i < 6; i++) {
                posX += 100;
                ventana.enemigos.add(new Enemy2(posX, 20, 80, 70, this));
            }
            posX = 0;
        } else if (puntos == 27 && nivel == 5) {
            nivel++;
            for (int i = 0; i < 8; i++) {
                posX += 80;
                ventana.enemigos.add(new Enemy3(posX, 20, 80, 70, this));
            }
            posX = 0;
        } else if (puntos == 35 && nivel == 6) {
            nivel++;
            for (int i = 0; i < 9; i++) {
                posX += 90;
                ventana.enemigos.add(new Enemy3(posX, 20, 80, 70, this));
            }
            posX = 0;
        } else if (puntos == 44 && nivel == 7) {
            nivel++;
            ventana.jefesFinales.add(new FinalBoss(posX, 40, 150, 135, this));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
