package subImagen;

import java.awt.Image;
import javax.swing.JButton;

/**
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class SubImagen {

    private int posicion;
    private Image subImagen;
    private boolean acertada;
    private JButton boton;

    public SubImagen(int posicion, Image subImage) {
        this.posicion = posicion;
        this.subImagen = subImage;
        this.acertada = false;
    }

    public int getPos() {
        return posicion;
    }

    public Image getImagen() {
        return subImagen;
    }

    public boolean estaAcertada() {
        return acertada;
    }

    public void marcarComoAcertada() {
        acertada = true;
    }

    public JButton getBoton() {
        return boton;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }

}
