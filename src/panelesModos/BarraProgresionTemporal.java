package panelesModos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;

/**
 * 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class BarraProgresionTemporal extends JProgressBar {

    //DECLARACIÓN ATRIBUTOS
    private int valorMinimo = 0;
    private int valorMaximo = 100;
    private final int ANCHO_BARRA = 40;

    //declaración método metodoPrincipal
    public BarraProgresionTemporal(int dimension) {
        super();
        //ASIGNACIÓN VALORES MÍNIMO Y MÁXIMO A LA JProgressBar barraTemporal
        setMinimum(valorMinimo);
        setMaximum(valorMaximo);
        //ASIGNACIÓN VALOR INICIAL A LA JProgressBar barraTemporal
        setValue(0);
        //ACTIVACIÓN VISUALIZACIÓN VALOR EN LA JProgressBar barraTemporal
        setStringPainted(true);
        //DIMENSIONAMIENTO JProgressBar barraTemporal
        setPreferredSize(new Dimension(dimension, ANCHO_BARRA));
        //ASIGNACIÓN COLORES DE FONDO Y TRAZADO JProgressBar barraTemporal
        setForeground(Color.RED);
        setBackground(Color.YELLOW);
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valor) {
        valorMaximo = valor;
        setMaximum(valorMaximo);
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(int valor) {
        valorMinimo = valor;
        setMinimum(valorMinimo);
    }

    public int getValorBarraTemporal() {
        return getValue();
    }

    public void setValorBarraTemporal(int valor) {
        setValue(valor);
    }

}
