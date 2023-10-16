package panelesModos;

import Estadisticas.Estadisticas;
import Estadisticas.EstadisticasInOut;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que representa un panel para mostrar el historial general de
 * estadísticas. Extiende JPanel. 
 * 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PanelHistGeneral extends JPanel {

    private Estadisticas[] aux; // Array para almacenar las estadísticas

    /**
     * Constructor de la clase PanelHistGeneral. Configura el diseño del panel
     * como BorderLayout y llama al método mostrarEstadisticas().
     */
    public PanelHistGeneral() {
        setLayout(new BorderLayout());
        mostrarEstadisticas(); // Método para mostrar las estadísticas
    }

    /**
     * Método que muestra las estadísticas en el panel.
     */
    private void mostrarEstadisticas() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        Font font = new Font(Font.MONOSPACED, Font.BOLD, 21); // Fuente utilizada para el texto

        textArea.setFont(font);

        // Leer las estadísticas desde algún lugar (posiblemente un archivo)
        aux = EstadisticasInOut.leerEstadisticas();
        String texto = "                                HISTORIAL GENERAL" + "\n" + "\n"; // Encabezado del historial

        // Agregar cada estadística al texto del historial
        for (int i = 0; i < aux.length && aux[i] != null; i++) {
            texto += aux[i] + "\n";
        }

        // Establecer el texto en el área de texto
        textArea.setText(texto);

        // Panel de desplazamiento para mostrar el área de texto
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 3000));

        add(scrollPane, BorderLayout.CENTER);
    }

}
