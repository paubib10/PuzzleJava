package panelesModos;

import Estadisticas.Estadisticas;
import Estadisticas.EstadisticasInOut;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que representa un panel para mostrar el historial selectivo de
 * estadísticas de un jugador. Extiende JPanel. 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PanelHistSelectivo extends JPanel {

    private EstadisticasInOut f;
    private Estadisticas[] aux;
    private CrearVentanas cr = new CrearVentanas();

    /**
     * Constructor de la clase PanelHistSelectivo. Configura el diseño del panel
     * como BorderLayout y llama al método initComponents().
     */
    public PanelHistSelectivo() {
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Inicializa los componentes del panel.
     */
    private void initComponents() {

        // Ventana emergente para solicitar el nombre del jugador
        String Nombre = ".";
        while (Nombre.equals(".")) {
            Nombre = cr.mostrarVentanaEmergente("HISTORIAL JUGADOR\nINTRODUCIR NOMBRE DE JUGADOR:");
        }
        if (Nombre.equals(";")) {
            imagUib();
            return;
        }

        // Crear un JTextArea para mostrar el historial
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);

        textArea.setFont(font);

        // Leer las estadísticas del jugador por nombre
        aux = f.leerEstadisticasNombre(Nombre);

        String texto = "                                  HISTORIAL DE " + Nombre.toUpperCase() + "\n" + "\n";

        // Construir el texto del historial concatenando las estadísticas
        for (int i = 0; i < aux.length && aux[i] != null; i++) {
            texto += aux[i] + "\n";
        }

        textArea.setText(texto);

        // Agregar el JTextArea dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Método para mostrar la imagen de la UIB.
     *
     * @return JLabel que contiene la imagen de la UIB.
     */
    private JLabel imagUib() {
        JLabel uibPic = new JLabel();
        // Obtener la imagen origen
        Image img = new ImageIcon(getClass().getResource("/iconos/UIB.jpg")).getImage();
        // Escalar la imagen
        Image newimg = img.getScaledInstance(1105, 800, java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);
        // Asignar la imagen al componente JLabel
        uibPic.setIcon(imageIcon);
        add(uibPic);

        return uibPic;
    }
}
