package juegopuzzle;

import javax.swing.JFrame;

/**
 * Clase principal que representa el programa de la Practica Final.
 * Extiende JFrame.
 * 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PuzzleJava extends JFrame {

    public static PanelContenidos panel = new PanelContenidos();
    
    /**
     * Método principal que inicia el programa.
     * Crea una instancia de la clase PRAFIN23 y la hace visible.
     * 
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        new PuzzleJava().setVisible(true);
    }
    
    public PuzzleJava() {
        setTitle("TALLER 2 - PROGRAMACIÓN II - CURSO 2022-2023");
        setResizable(false);
        setSize(1350, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setContentPane(panel);
    }
}