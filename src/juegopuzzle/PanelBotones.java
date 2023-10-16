package juegopuzzle;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import panelesModos.CrearVentanas;

/**
 * Clase que representa un panel de botones en la interfaz gráfica. Extiende la
 * clase JPanel e implementa la interfaz ActionListener.
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PanelBotones extends JPanel implements ActionListener {

    String[] buttonTexts = {"NUEVA PARTIDA", "HISTORIAL GENERAL", "HISTORIAL SELECTIVO", "SALIR"};
    private CrearVentanas cr = new CrearVentanas();

    public PanelBotones() {
        setLayout(new GridLayout(4, 1));
        initComponents();
    }

    private void initComponents() {
        // Crear y agregar los botones al panel
        for (String buttonText : buttonTexts) {
            JButton button = new JButton(buttonText);
            button.setBackground(Color.black);
            button.setFont(new java.awt.Font("Dialog", 1, 12));
            button.setForeground(Color.white);
            button.addActionListener(this);
            add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Manejar los eventos de acción generados por los botones
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
                PuzzleJava.panel.cambiarModoHistSelectivo();
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
