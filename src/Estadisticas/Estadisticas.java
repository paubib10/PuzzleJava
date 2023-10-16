package Estadisticas;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class Estadisticas implements Serializable {

    private String Nombre;
    private static Date date;
    private int puntuacion;
    public static final Estadisticas centinela = new Estadisticas("zzz", 999);
    private String s;

    public Estadisticas() {
        Nombre = null;

        Date date;
        puntuacion = 0;
    }

    public Estadisticas(String n, int p) {
        Nombre = n.toUpperCase();
        date = new Date();
        s = date.toString();
        puntuacion = p;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String n) {
        Nombre = n.toUpperCase();
    }

    public String getDate() {
        return s;
    }

    public void setDate(Date d) {
        date = d;

        s = date.toString();

    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int p) {
        puntuacion = p;
    }

    public boolean esCentinela() {
        if (Estadisticas.centinela == this) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String formato = " NOMBRE: %-15s - FECHA: %-29s - PUNTOS: %d";

        String resultado = String.format(formato, getNombre().toUpperCase(), getDate(), getPuntuacion());

        return resultado;
    }

}
