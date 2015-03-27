
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import logger.Main;

/**
 * @author Laurens van den Bercken
 */
public class Listener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String buttonString = e.getActionCommand();
        
        if(buttonString.equals("Exit")) {
            System.exit(0);
        }
        else if (buttonString.equals("Start")) {
            try {
                Main.start();
            } catch (NumberFormatException | IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (buttonString.equals("Finish")) {
            try {
                Main.stop();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("Unexpected event.");
        }
    }
    
}
