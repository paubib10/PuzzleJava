package panelesModos;

import Estadisticas.Estadisticas;
import Estadisticas.EstadisticasInOut;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import panelesModos.BarraProgresionTemporal;
import juegopuzzle.PuzzleJava;
import static panelesModos.CrearVentanas.validarNombre;
import subImagen.PanelSubImagenes;
import subImagen.SubImagen;

/**
 * Clase Partida que extiende de JPanel y representa un panel de juego.
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class Partida extends JPanel {

    public static String nombre;
    private Font fuente = new Font("Dialog", Font.BOLD, 12);
    private BarraProgresionTemporal barraProgresionTemporal;
    public String Nombre;
    private CrearVentanas cr = new CrearVentanas();
    public static Thread progresoThread;
    public static boolean iniPartida = false;

    /**
     * Crea un nuevo panel de juego. Configura el diseño del panel y inicializa
     * sus componentes.
     */
    public Partida() {
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Inicializa los componentes del panel de juego y muestra una ventana
     * emergente para que el jugador introduzca los datos necesarios. Si se
     * ingresan los valores correctamente, se cierra la ventana emergente y se
     * crea el panel de subimágenes. Si se cancela la ventana emergente, se
     * muestra una imagen por defecto (imagen uib).
     */
    private void initComponents() {
        // Crear componentes de la ventana emergente
        JTextField nombreTextField = new JTextField();
        JTextField subdivisionHorizontalTextField = new JTextField();
        JTextField subdivisionVerticalTextField = new JTextField();

        JButton confirmarBoton = new JButton("CONFIRMAR");
        confirmarBoton.setPreferredSize(new Dimension(200, 40));

        // Configurar el diseño de la ventana emergente
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBackground(Color.BLACK);
        JLabel nombreText = new JLabel("NOMBRE DEL JUGADOR:");
        nombreText.setFont(fuente);
        nombreText.setForeground(Color.WHITE);
        panel.add(nombreText);
        panel.add(nombreTextField);

        JLabel subHor = new JLabel("NÚMERO DE SUBDIVISIONES HORIZONTAL:");
        subHor.setFont(fuente);
        subHor.setForeground(Color.WHITE);
        panel.add(subHor);
        panel.add(subdivisionHorizontalTextField);

        JLabel subVer = new JLabel("NÚMERO DE SUBDIVISIONES VERTICAL:");
        subVer.setFont(fuente);
        subVer.setForeground(Color.WHITE);
        panel.add(subVer);
        panel.add(subdivisionVerticalTextField);

        JPanel panelVentana = new JPanel();
        panelVentana.setLayout(new BorderLayout());
        panelVentana.add(panel, BorderLayout.CENTER);
        panelVentana.add(confirmarBoton, BorderLayout.SOUTH);

        // Crear la ventana emergente
        JDialog dialog = new JDialog((Dialog) null, "INTRODUCCIÓN DATOS", true);
        dialog.setContentPane(panelVentana);
        dialog.setPreferredSize(new Dimension(600, 200));
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        // Configurar acción del botón "Continuar"
        confirmarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados por el usuario
                int subdivisionHorizontal;
                int subdivisionVertical;

                if (nombreTextField.getText().equals("") || subdivisionHorizontalTextField.getText().equals("") || subdivisionVerticalTextField.getText().equals("")) {
                    // Manejo de error si el usuario no ingresó valores
                    cr.mostrarError("RELLENA TODOS LOS CAMPOS");
                    return;
                } else {
                    Nombre = nombreTextField.getText();
                }

                if (!validarNombre(Nombre)) {
                    cr.mostrarError("INGRESE UN NOMBRE VALIDO");
                    return;
                }

                try {
                    subdivisionHorizontal = Integer.parseInt(subdivisionHorizontalTextField.getText());
                    subdivisionVertical = Integer.parseInt(subdivisionVerticalTextField.getText());

                    // Verificar si alguna de las subdivisiones es 0 o 1
                    if (subdivisionHorizontal == 0 || subdivisionVertical == 0 || subdivisionHorizontal == 1 || subdivisionVertical == 1) {
                        cr.mostrarError("LAS SUBDIVISIONES DEBEN SER MAYORES A 1");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    // Manejo de error si el usuario no ingresó un número válido
                    cr.mostrarError("INTRODUCE CORRECTAMENTE LAS SUBDIVISIONES");
                    return;
                }

                // Cerrar la ventana emergente
                dialog.dispose();

                // Crear el PanelSubImagenes y dividir la imagen en subdivisiones
                crearPanelSubImagenes(subdivisionHorizontal, subdivisionVertical, Nombre);
                iniPartida = true;
            }
        });

        // Configurar acción al cerrar la ventana emergente
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                add(imagUib());
            }
        });
        // Mostrar la ventana emergente
        dialog.setVisible(true);
    }

    /**
     * Crea un panel de subimágenes a partir de una imagen aleatoria de una
     * carpeta especificada. Las subdivisiones se especifican mediante el número
     * de subdivisiones horizontales y verticales.
     *
     * @param subdivisionesHorizontal el número de subdivisiones horizontales
     * @param subdivisionesVertical el número de subdivisiones verticales
     */
    public void crearPanelSubImagenes(int subdivisionesHorizontal, int subdivisionesVertical, String Nombre) {
        // Obtenemos una imagen aleatoria de la carpeta especificada o de la carpeta predeterminada
        String ruta = juegopuzzle.IconosMenu.RutaDirectorioSeleccionado;
        if (ruta == null) {
            ruta = "src/imagenes";
        }
        BufferedImage imagen = getRandomImageFromFolder(ruta);

        // Creamos un panel de subimágenes con las subdivisiones especificadas
        PanelSubImagenes panel = new PanelSubImagenes(subdivisionesHorizontal, subdivisionesVertical);

        // Calcular las dimensiones de cada subdivisión
        int subdivisionWidth = imagen.getWidth() / subdivisionesHorizontal;
        int subdivisionHeight = imagen.getHeight() / subdivisionesVertical;

        // Creamos las subimágenes y agregarlas al panel
        for (int i = 0; i < subdivisionesVertical; i++) {
            for (int j = 0; j < subdivisionesHorizontal; j++) {
                // Calculamos las coordenadas de la subimagen
                int x = j * subdivisionWidth;
                int y = i * subdivisionHeight;

                // Obtenemos la subimagen correspondiente a esta subdivisión
                BufferedImage subImagen = imagen.getSubimage(x, y, subdivisionWidth, subdivisionHeight);

                // Redimensionamos la subimagen al tamaño deseado
                int resizedWidth = 1100 / subdivisionesHorizontal;
                int resizedHeight = 710 / subdivisionesVertical;
                Image resizedImage = subImagen.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);

                // Creamos un objeto SubImagen y agregarlo al panel
                SubImagen sub = new SubImagen(i * subdivisionesHorizontal + j, resizedImage);
                panel.agregarSubImagen(sub, Nombre);
            }
        }

        // Agregamos una barra de progresión temporal al panel
        barraProgresionTemporal = new BarraProgresionTemporal(300);
        panel.add(barraProgresionTemporal);

        // Calculamos el tiempo de pausa en función del número de subdivisiones
        int subdivisionesTotales = subdivisionesHorizontal * subdivisionesVertical;
        int tiempoPausa = 15 * subdivisionesTotales;

        // Iniciamos un hilo para actualizar la barra de progreso
        progresoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Actualizamos la barra de progreso en incrementos hasta llegar al 100%
                for (int i = 0; i <= 100; i++) {
                    barraProgresionTemporal.setValorBarraTemporal(i);

                    try {
                        Thread.sleep(tiempoPausa);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Mostramos un mensaje de tiempo agotado y cargar la imagen completa con el botón "CONTINUAR"
                cr.mostrarMensaje("NO LO HAS CONSEGUIDO - EL TIEMPO HA TERMINADO");
                cargarImagenCompleta(imagen);

                //Guardamos la partida con un puntaje 0.
                Estadisticas x = new Estadisticas(Nombre, 0);
                EstadisticasInOut f = new EstadisticasInOut();
                f.guardarEstadisticas(x);  //guar
            }
        });
        progresoThread.start();

        // Creamos un separador JSplitPane con el panel de subimágenes y la barra de progreso temporal
        JSplitPane separador4 = new JSplitPane(VERTICAL_SPLIT, panel, barraProgresionTemporal);
        separador4.setEnabled(false);
        separador4.setDividerLocation(725);

        add(separador4);
    }

    /**
     * Carga una imagen completa en el panel y muestra un botón de "CONTINUAR".
     *
     * @param imagen la imagen a cargar en el panel
     */
    public void cargarImagenCompleta(BufferedImage imagen) {
        // Eliminamos todos los componentes actuales del panel
        removeAll();

        // Creamos un componente JLabel para mostrar la imagen
        JLabel imagenRandomEntera = new JLabel();

        // Escalamos la imagen a un tamaño específico
        Image newimg = imagen.getScaledInstance(1105, 725, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);
        imagenRandomEntera.setIcon(imageIcon);

        // Creamos un botón de "CONTINUAR" con estilo personalizado
        JButton continuarBoton = new JButton("CONTINUAR");
        continuarBoton.setBackground(Color.BLACK);
        continuarBoton.setFont(fuente);
        continuarBoton.setForeground(Color.WHITE);
        continuarBoton.setPreferredSize(new Dimension(200, 40));

        // Definimos la acción al hacer clic en el botón "CONTINUAR"
        continuarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realizamos la acción deseada al hacer clic en el botón "CONTINUAR"
                PuzzleJava.panel.cambiarModoDefault();
                iniPartida = false;
            }
        });

        // Creamos un separador JSplitPane que muestra la imagen y el botón en un panel dividido verticalmente
        JSplitPane separador4 = new JSplitPane(VERTICAL_SPLIT, imagenRandomEntera, continuarBoton);
        separador4.setEnabled(false);
        separador4.setDividerLocation(725);

        add(separador4);

        // Actualizamos el contenido del panel y vuelve a dibujar
        revalidate();
        repaint();
    }

    /**
     * Obtiene una imagen aleatoria de una carpeta especificada o de la carpeta
     * predeterminada.
     *
     * @param folderPath la ruta de la carpeta desde donde se obtendrá la imagen
     * aleatoria
     * @return la imagen aleatoria obtenida de la carpeta especificada o de la
     * carpeta predeterminada, o null si no se encontraron imágenes
     */
    public BufferedImage getRandomImageFromFolder(String folderPath) {
        // Obtenemos la carpeta
        File folder = new File(folderPath);

        // Obtenemos la lista de archivos de imagen en la carpeta
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().matches(".+\\.(jpg|jpeg|png|gif)");
            }
        });

        // Si se encontraron archivos de imagen
        if (files != null && files.length > 0) {
            // Obtenemos un archivo aleatorio de la lista
            File randomFile = files[new Random().nextInt(files.length)];

            try {
                // Leemos y devolvemos la imagen del archivo aleatorio
                return ImageIO.read(randomFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Mostramos un mensaje de error y establecer la carpeta predeterminada
        cr.mostrarMensaje("EL DIRECTORIO ESTABLECIDO ANTERIORMENTE ES ERRÓNEO\nSE HA ESTABLECIDO EL DIRECTORIO PREDETERMINADO");
        String defaultFolderPath = "src/imagenes";
        File defaultFolder = new File(defaultFolderPath);

        // Obtenemos la lista de archivos de imagen en la carpeta predeterminada
        File[] defaultFiles = defaultFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().matches(".+\\.(jpg|jpeg|png|gif)");
            }
        });

        // Si se encontraron archivos de imagen en la carpeta predeterminada
        if (defaultFiles != null && defaultFiles.length > 0) {
            // Obtenemos un archivo aleatorio de la lista
            File defaultRandomFile = defaultFiles[new Random().nextInt(defaultFiles.length)];

            try {
                // Leemos y devolvemos la imagen del archivo aleatorio de la carpeta predeterminada
                return ImageIO.read(defaultRandomFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Si no se encontraron imágenes, devolvemos null
        return null;
    }

    private JLabel imagUib() {
        JLabel uibPic = new JLabel();
        //imagen origen
        Image img = new ImageIcon(getClass().getResource("/iconos/UIB.jpg")).getImage();
        //escala imagen
        Image newimg = img.getScaledInstance(1105, 800, java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);
        //asigna a componenente JLabel
        uibPic.setIcon(imageIcon);

        return uibPic;
    }

}
