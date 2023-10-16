package juegopuzzle;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pau Toni Bibiloni Martínez y Nasim Hosan Benyacoub Terki
 */
public class PanelVisualizaciones extends JPanel {
    
    private JLabel uibPic;
    
    public PanelVisualizaciones(){
        setLayout(new BorderLayout());
        initComponents();
    }
    
    public void initComponents(){
        uibPic = new JLabel();
        //imagen origen
        Image img = new ImageIcon(getClass().getResource("/iconos/UIB.jpg")).getImage();
        //escala imagen
        Image newimg = img.getScaledInstance(1105, 800, java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);
        //asigna a componenente JLabel
        uibPic.setIcon(imageIcon);

        add(uibPic);

    }
    
}
