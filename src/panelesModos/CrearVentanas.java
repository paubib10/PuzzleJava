package panelesModos;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Clase que contiene métodos para crear y mostrar ventanas emergentes en el
 * programa. Permite mostrar mensajes, mostrar errores y solicitar entrada al
 * usuario. También incluye métodos para validar nombres y verificar si una
 * cadena es un número o contiene signos. 
 * 
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class CrearVentanas {

    public CrearVentanas() {

    }

    /**
     * Muestra un mensaje en una ventana emergente.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Muestra una ventana emergente para solicitar una entrada al usuario.
     *
     * @param mensaje El mensaje que se muestra en la ventana.
     * @return El valor ingresado por el usuario como una cadena de texto.
     */
    public static String mostrarVentanaEmergente(String mensaje) {
        // Crear componentes de la ventana emergente
        JTextField nombreTextField = new JTextField();

        // Crear el objeto JOptionPane
        Object[] components = {mensaje, nombreTextField};
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        int option = JOptionPane.showOptionDialog(
                null,
                components,
                "Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        String nombre = "";

        // Verificar la opción seleccionada
        if (option == JOptionPane.OK_OPTION) {
            nombre = nombreTextField.getText();

            if (validarNombre(nombre)) {
                // Nombre válido
            } else {
                nombre = ".";
                // Nombre inválido: mostrar mensaje de error
                mostrarError("INGRESE UN NOMBRE VALIDO");
            }
        } else {
            // Acción cancelada          
            nombre = ";";
        }

        return nombre;
    }

    /**
     * Muestra un mensaje de error en una ventana emergente.
     *
     * @param mensaje El mensaje de error a mostrar.
     */
    static public void mostrarError(String mensaje) {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Valida un nombre ingresado por el usuario.
     *
     * @param nombre El nombre a validar.
     * @return true si el nombre es válido, false de lo contrario.
     */
    public static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.isEmpty() && !esNumero(nombre) && !tieneSignos(nombre);
    }

    /**
     * Verifica si una cadena es un número.
     *
     * @param cadena La cadena a verificar.
     * @return true si la cadena es un número, false de lo contrario.
     */
    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si una cadena contiene signos.
     *
     * @param cadena La cadena a verificar.
     * @return true si la cadena contiene signos, false de lo contrario.
     */
    public static boolean tieneSignos(String cadena) {
        return cadena.matches(".*[-!@#$%^&*()+_=\\[\\]{};':\"\\\\|,.<>/?].*");
    }
}
