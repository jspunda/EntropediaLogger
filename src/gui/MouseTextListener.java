
package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

/**
 * @author Laurens van den Bercken
 */
public class MouseTextListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        JTextField field = (JTextField) e.getSource();
        field.selectAll();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
