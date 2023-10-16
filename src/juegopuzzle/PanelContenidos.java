package juegopuzzle;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import panelesModos.PanelHistGeneral;
import panelesModos.PanelHistSelectivo;
import panelesModos.Partida;

/**
 * Representa un panel que contiene los distintos componentes y actividades del
 * programa. Extiende JPanel.
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PanelContenidos extends JPanel {

    private static JSplitPane separador1, separador2, separador3;

    /**
     * Constructor de la clase PanelContenidos. Configura el diseño del panel
     * como BorderLayout y llama al método initComponents().
     */
    public PanelContenidos() {
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Inicializa los componentes del panel. Crea y configura los paneles
     * separadores (JSplitPane) y los agrega al panel principal.
     */
    private void initComponents() {
        separador1 = new JSplitPane(HORIZONTAL_SPLIT, new PanelBotones(), new PanelVisualizaciones());
        separador1.setEnabled(false);
        separador1.setDividerLocation(215);

        separador2 = new JSplitPane(VERTICAL_SPLIT, menuArriba(), separador1);
        separador2.setEnabled(false);
        separador2.setDividerLocation(50);

        separador3 = new JSplitPane(VERTICAL_SPLIT);
        separador3.setEnabled(false);
        separador3.setTopComponent(separador2);
        separador3.setDividerLocation(600);

        add(separador3, BorderLayout.CENTER);
    }

    /**
     * Cambia al modo de juego. Reemplaza el PanelVisualizaciones por el Partida
     * en el separador1.
     */
    public void cambiarModoJuego() {
        separador1.setRightComponent(new Partida());
        separador1.setDividerLocation(215);
        separador1.revalidate();
        separador1.repaint();
    }

    /**
     * Cambia al modo de historial general. Reemplaza el PanelVisualizaciones
     * por el PanelHistGeneral en el separador1.
     */
    public void cambiarModoHistGeneral() {
        separador1.setRightComponent(new PanelHistGeneral());
        separador1.setDividerLocation(215);
        separador1.revalidate();
        separador1.repaint();
    }

    /**
     * Cambia al modo de historial selectivo. Reemplaza el PanelVisualizaciones
     * por el PanelHistSelectivo en el separador1.
     */
    public void cambiarModoHistSelectivo() {
        separador1.setRightComponent(new PanelHistSelectivo());
        separador1.setDividerLocation(215);
        separador1.revalidate();
        separador1.repaint();
    }

    /**
     * Cambia al modo predeterminado. Reemplaza el PanelVisualizaciones por el
     * Partida en el separador1.
     */
    public void cambiarModoDefault() {
        separador1.setRightComponent(new PanelVisualizaciones());
        separador1.setDividerLocation(215);
        separador1.revalidate();
        separador1.repaint();
    }

    /**
     * Crea el panel de menú en la parte superior.
     *
     * @return El panel que contiene el menú.
     */
    private JPanel menuArriba() {
        JPanel contenedorMenu = new JPanel(new BorderLayout());
        contenedorMenu.add(new MenuDesplegable(), BorderLayout.NORTH);
        contenedorMenu.add(new IconosMenu(), BorderLayout.SOUTH);
        return contenedorMenu;
    }
}
