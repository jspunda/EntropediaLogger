package gui;

/**
 * @author Laurens van den Bercken
 */

import java.awt.Component;
import javax.swing.JOptionPane;

public class PopUp {

    public static void infoBox(String infoMessage, String titleBar, Component c) {
        JOptionPane.showMessageDialog(c, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
