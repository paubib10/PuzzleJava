package juegopuzzle;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import panelesModos.CrearVentanas;

/**
 * Clase que representa un menú desplegable en la interfaz gráfica. Extiende la
 * clase JMenuBar e implementa la interfaz ActionListener.
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class MenuDesplegable extends JMenuBar implements ActionListener {

    private JMenu menu;
    private JMenuItem novaPartidaBoto;
    private JMenuItem classificacioBoto; 
    private JMenuItem historialBoto; 
    private JMenuItem canviarDirectoriBoto; 
    private JMenuItem sortirBoto;
    Border borde = BorderFactory.createLineBorder(Color.white);
    private CrearVentanas cr = new CrearVentanas();
    private IconosMenu cD = new IconosMenu();

    public MenuDesplegable() {
        initComponents();
    }

    private void initComponents() {
        // Crear el menú principal
        menu = new JMenu("MENÚ");
        menu.setBackground(Color.black);
        menu.setForeground(Color.white);
        menu.setBorder(borde);

        // Crear las opciones del menú
        novaPartidaBoto = createMenuItem("NUEVA PARTIDA");
        novaPartidaBoto.addActionListener(this);

        classificacioBoto = createMenuItem("HISTORIAL GENERAL");
        classificacioBoto.addActionListener(this);

        historialBoto = createMenuItem("HISTORIAL SELECTIVO");
        historialBoto.addActionListener(this);

        canviarDirectoriBoto = createMenuItem("CAMBIAR DIRECTORIO DE IMÁGENES");
        canviarDirectoriBoto.addActionListener(this);

        sortirBoto = createMenuItem("SALIR");
        sortirBoto.addActionListener(this);

        // Agregar las opciones al menú principal
        addMenuItems(menu, novaPartidaBoto, classificacioBoto, historialBoto, canviarDirectoriBoto, sortirBoto);

        // Agregar el menú a la barra de menú
        add(menu);
        setBackground(Color.black);
    }

    /**
     * Crea un objeto JMenuItem con el texto proporcionado.
     *
     * @param text El texto del elemento de menú.
     * @return El objeto JMenuItem creado.
     */
    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setBackground(Color.black);
        menuItem.setFont(new Font("Dialog", Font.BOLD, 10));
        menuItem.setForeground(Color.white);
        return menuItem;
    }

    /**
     * Agrega los elementos de menú al menú principal.
     *
     * @param menu El menú principal.
     * @param items Los elementos de menú a agregar.
     */
    private void addMenuItems(JMenu menu, JMenuItem... items) {
        int itemCount = items.length;
        for (int i = 0; i < itemCount; i++) {
            JMenuItem item = items[i];
            menu.add(item);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Manejar los eventos de acción generados por los elementos de menú
        switch (evento.getActionCommand()) {
            case "NUEVA PARTIDA":
                if (panelesModos.Partida.iniPartida) {
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                PuzzleJava.panel.cambiarModoJuego();
                break;
            case "HISTORIAL GENERAL":
                if (panelesModos.Partida.iniPartida) {
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                PuzzleJava.panel.cambiarModoHistGeneral();
                break;
            case "HISTORIAL SELECTIVO":
                if (panelesModos.Partida.iniPartida) {
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                String Nombre = "Pau";
                PuzzleJava.panel.cambiarModoHistSelectivo();
                break;
            case "CAMBIAR DIRECTORIO DE IMÁGENES":
                if (panelesModos.Partida.iniPartida) {
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                cD.seleccionarDirectorio(); // Reutilizamos el metodo de la clase IconosMenu
                break;
            case "SALIR":
                if (panelesModos.Partida.iniPartida) {
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                System.exit(0); // Sale del programa
                break;
        }
    }
}
