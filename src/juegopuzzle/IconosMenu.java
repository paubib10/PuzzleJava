package juegopuzzle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import panelesModos.CrearVentanas;

/**
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class IconosMenu extends JToolBar {

    private JButton novaPartidaIcona;
    private JButton classificacioIcona;
    private JButton historialIcona;
    private JButton canviarDirectoriIcona;
    private JButton sortirIcona;
    Border borde = BorderFactory.createLineBorder(Color.white);
    private CrearVentanas cr = new CrearVentanas();
    public static String RutaDirectorioSeleccionado;

    public IconosMenu() {
        initComponents();
    }

    private void initComponents() {
        novaPartidaIcona = createToolbarButton("/iconos/iconoNuevaPartida.jpg");
        novaPartidaIcona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelesModos.Partida.iniPartida) {
                    // La partida está en curso, no se permite iniciar una nueva partida
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                PuzzleJava.panel.cambiarModoJuego();
            }
        });

        classificacioIcona = createToolbarButton("/iconos/iconoHistorialGeneral.jpg");
        classificacioIcona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelesModos.Partida.iniPartida) {
                    // La partida está en curso, no se permite iniciar una nueva partida
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                PuzzleJava.panel.cambiarModoHistGeneral();
            }
        });

        historialIcona = createToolbarButton("/iconos/iconoHistorialSelectivo.jpg");
        historialIcona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelesModos.Partida.iniPartida) {
                    // La partida está en curso, no se permite iniciar una nueva partida
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                String Nombre = "Pau";
                PuzzleJava.panel.cambiarModoHistSelectivo();
            }
        });

        canviarDirectoriIcona = createToolbarButton("/iconos/iconoCambiarDIrectorio.jpg");
        canviarDirectoriIcona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelesModos.Partida.iniPartida) {
                    // La partida está en curso, no se permite iniciar una nueva partida
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                seleccionarDirectorio();
            }
        });

        sortirIcona = createToolbarButton("/iconos/iconoSalir.jpg");
        sortirIcona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelesModos.Partida.iniPartida) {
                    // La partida está en curso, no se permite iniciar una nueva partida
                    cr.mostrarMensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                    return;
                }
                System.exit(0); // Sale del programa
            }
        });

        setBackground(Color.black);
        setFloatable(false);
        setForeground(Color.white);
        setRollover(true);
        setBorder(borde);

        add(novaPartidaIcona);
        add(classificacioIcona);
        add(historialIcona);
        add(canviarDirectoriIcona);
        add(sortirIcona);
    }

    private JButton createToolbarButton(String iconPath) {
        JButton button = new JButton(new ImageIcon(getClass().getResource(iconPath)));
        button.setBackground(Color.black);
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    public void seleccionarDirectorio() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = chooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File directorioSeleccionado = chooser.getSelectedFile();

            RutaDirectorioSeleccionado = directorioSeleccionado.getAbsolutePath();
        }
    }
}
