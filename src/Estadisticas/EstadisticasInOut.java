package Estadisticas;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase EstadisticasInOut que se encarga de leer y escribir objetos de tipo
 Estadisticas en un archivo binario.
 * 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class EstadisticasInOut {

    private static final String FILE_PATH = "estadisticas.dat";

    /**
     * Método para guardar una estadística en el archivo binario. Lee todas las
     * estadísticas existentes, las guarda en un array, y luego escribe el nuevo
     * objeto al final.
     *
     * @param x La estadística a guardar.
     */
    public static void guardarEstadisticas(Estadisticas x) {
        Estadisticas[] p = new Estadisticas[1000];
        boolean fidefitxer = false;

        // Leemos todos los elementos y los guardo en un array para luego escribirlos.
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            int i = 0;
            do {
                try {
                    aumentarArray(p, i);
                    p[i++] = (Estadisticas) ois.readObject();
                } catch (EOFException eofe) {
                    fidefitxer = true;
                }
            } while (!fidefitxer);
            ois.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Escribimos los elementos guardados y la nueva estadística al final del archivo.
        try {
            FileOutputStream sortida = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(sortida);

            int i = 0;
            while (p[i] != null) {
                oos.writeObject(p[i++]);
            }
            oos.writeObject(x);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Método para leer todas las estadísticas almacenadas en el archivo
     * binario.
     *
     * @return Un array de objetos Estadisticas con todas las estadísticas
     * leídas.
     */
    public static Estadisticas[] leerEstadisticas() {
        Estadisticas p;
        Estadisticas[] array = new Estadisticas[100];
        boolean fidefitxer = false;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            int i = 0;
            do {
                try {
                    p = (Estadisticas) ois.readObject();
                    aumentarArray(array, i);
                    array[i++] = p;
                } catch (EOFException eofe) {
                    fidefitxer = true;
                }
            } while (!fidefitxer);
            ois.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return array;
    }

    /**
     * Método para leer las estadísticas de un nombre específico.
     *
     * @param s El nombre para buscar las estadísticas.
     * @return Un array de objetos Estadisticas que coinciden con el nombre
     * especificado.
     */
    public static Estadisticas[] leerEstadisticasNombre(String s) {
        Estadisticas p;
        s = s.toUpperCase();
        Estadisticas[] array = new Estadisticas[100];
        boolean fidefitxer = false;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            int i = 0;
            do {
                try {
                    p = (Estadisticas) ois.readObject();
                    p.setNombre(p.getNombre().toUpperCase());

                    if (p.getNombre().equals(s)) {
                        aumentarArray(array, i);
                        array[i++] = p;
                    }
                } catch (EOFException eofe) {
                    fidefitxer = true;
                }
            } while (!fidefitxer);
            ois.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return array;
    }

    /**
     * Método para aumentar el tamaño de un array de estadísticas si está lleno.
     *
     * @param d El array a aumentar.
     * @param i El índice actual del array.
     */
    public static void aumentarArray(Estadisticas[] d, int i) {
        if (i == d.length) {
            Estadisticas[] aux = new Estadisticas[d.length + 50];
            for (int j = 0; j < d.length; j++) {
                aux[j] = d[j];
            }
            d = aux;
        }
    }

}
