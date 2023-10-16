package subImagen;

import Estadisticas.Estadisticas;
import Estadisticas.EstadisticasInOut;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import panelesModos.Partida;
import juegopuzzle.PuzzleJava;

/**
 * Panel que contiene las subimágenes del juego del puzzle. Permite la
 * interacción del jugador con las subimágenes. Controla la lógica del juego.
 * Extiende la clase JPanel. Implementa el interfaz MouseListener para gestionar
 * los eventos del ratón.
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PanelSubImagenes extends JPanel {

    public int puntos;
    private SubImagen[] subImagenes;
    private int cantidadSubImagenes;
    private JButton subImagenPresionada;

    /**
     * Constructor por defecto de la clase panelSubImagenes. Inicializa los
     * atributos del panelSubImagenes.
     */
    public PanelSubImagenes() {

    }

    /**
     * Constructor de la clase panelSubImagenes. Configura el diseño del panel y
     * crea las subimágenes según las subdivisiones dadas.
     *
     * @param subdivisionHorizontal Número de subdivisiones horizontales.
     * @param subdivisionVertical Número de subdivisiones verticales.
     */
    public PanelSubImagenes(int subdivisionHorizontal, int subdivisionVertical) {
        setLayout(new GridLayout(subdivisionVertical, subdivisionHorizontal));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cantidadSubImagenes = subdivisionHorizontal * subdivisionVertical;
        subImagenes = new SubImagen[cantidadSubImagenes];

        // Crear un borde negro
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 4);
        setBorder(borde);
    }

    /**
     * Agrega una subimagen al panelSubImagenes. Crea un botón con la imagen de
     * la subimagen y lo agrega al panel. Asigna el evento del ratón al botón
     * para permitir la interacción con la subimagen.
     *
     * @param subImagen Subimagen a agregar.
     * @param Nombre Nombre del jugador.
     */
    public void agregarSubImagen(SubImagen subImagen, String Nombre) {
        for (int i = 0; i < cantidadSubImagenes; i++) {
            if (subImagenes[i] == null) {
                subImagenes[i] = subImagen;
                JButton subImagenBoton = new JButton();
                subImagenBoton.setIcon(new ImageIcon(subImagen.getImagen()));
                subImagenBoton.addMouseListener(eventosRaton(Nombre));

                // Agregar borde a la subimagen
                Border borde = BorderFactory.createCompoundBorder(
                        new LineBorder(java.awt.Color.WHITE),
                        new EmptyBorder(2, 2, 2, 2)
                );
                subImagenBoton.setBorder(borde);

                add(subImagenBoton);
                break;
            }
        }

        // Mezclar las subImágenes en orden aleatorio
        for (int i = subImagenes.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            SubImagen temp = subImagenes[i];
            subImagenes[i] = subImagenes[randomIndex];
            subImagenes[randomIndex] = temp;
        }

        // Reorganizar los botones en el panel según el nuevo orden de subImágenes
        removeAll();
        for (SubImagen subImage : subImagenes) {
            if (subImage != null) {
                JButton subImagenBoton = new JButton();
                subImagenBoton.setIcon(new ImageIcon(subImage.getImagen()));
                subImagenBoton.addMouseListener(eventosRaton(Nombre));
                subImagenBoton.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(java.awt.Color.WHITE),
                        new EmptyBorder(2, 2, 2, 2)
                ));
                add(subImagenBoton);
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Crea el evento del ratón para la interacción con las subimágenes.
     *
     * @param Nombre Nombre del jugador.
     * @return MouseListener para gestionar los eventos del ratón.
     */
    public MouseListener eventosRaton(String Nombre) {
        MouseListener accion = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent evento) {
                JButton subImagenBoton = (JButton) evento.getSource();
                if (subImagenPresionada == null) {
                    subImagenPresionada = subImagenBoton;
                    subImagenPresionada.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    intercambiarSubImagenes(subImagenPresionada, subImagenBoton, Nombre);
                    subImagenPresionada.setBorder(BorderFactory.createCompoundBorder(
                            new LineBorder(java.awt.Color.WHITE),
                            new EmptyBorder(2, 2, 2, 2)
                    ));
                    subImagenPresionada = null;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        return accion;
    }

    /**
     * Intercambia dos subimágenes y actualiza el estado del juego. Calcula los
     * puntos obtenidos y guarda las estadísticas si todas las subimágenes están
     * en la posición correcta.
     *
     * @param subImagen1 Primera subimagen a intercambiar.
     * @param subImagen2 Segunda subimagen a intercambiar.
     * @param Nombre Nombre del jugador.
     */
    @SuppressWarnings("deprecation")
    private void intercambiarSubImagenes(JButton subImagen1, JButton subImagen2, String Nombre) {

        // Obtener las posiciones en el panel de las subimágenes
        int posicion1 = -1;
        int posicion2 = -1;
        for (int i = 0; i < subImagenes.length; i++) {
            if (subImagenes[i] != null) {
                JButton boton = (JButton) getComponent(i);
                if (boton == subImagen1) {
                    posicion1 = i;
                } else if (boton == subImagen2) {
                    posicion2 = i;
                }
            }
        }

        int posiciones = subImagenes.length;

        // Realizar el intercambio de posiciones e imágenes
        if (posicion1 != -1 && posicion2 != -1) {
            // Intercambiar posiciones en el array de subImágenes
            SubImagen sI = subImagenes[posicion1];
            subImagenes[posicion1] = subImagenes[posicion2];
            subImagenes[posicion2] = sI;

            // Intercambiar imágenes en los botones
            ImageIcon tempIcon = (ImageIcon) subImagen1.getIcon();
            subImagen1.setIcon(subImagen2.getIcon());
            subImagen2.setIcon(tempIcon);

            // Verificar si todas las subImágenes están en la posición correcta
            if (todasSubImagenesEnPosicionCorrecta()) {
                EstadisticasInOut f = new EstadisticasInOut();
                int puntos = posiciones * 3;

                f.guardarEstadisticas(new Estadisticas(Nombre, (puntos)));
                Partida.progresoThread.stop();
                mostrarMensaje("¡ENHORABUENA! LO HAS CONSEGUIDO\nHAS OBTENIDO " + puntos + " PUNTOS");
                PuzzleJava.panel.cambiarModoDefault();
                Partida.iniPartida = false;
            }
        }
    }

    /**
     * Comprueba si todas las subimágenes están en la posición correcta.
     *
     * @return true si todas las subimágenes están en la posición correcta,
     * false en caso contrario.
     */
    private boolean todasSubImagenesEnPosicionCorrecta() {
        for (int i = 0; i < subImagenes.length; i++) {
            SubImagen subImagen = subImagenes[i];
            if (subImagen == null || subImagen.getPos() != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * Crea una ventana emergente que muestre el mensaje que se pasa por
     * parametro.
     *
     * @param mensaje Mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        JOptionPane.showMessageDialog(this, mensaje);
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
    }

}
