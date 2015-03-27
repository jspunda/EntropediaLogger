
package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logger.Main;

/**
 * @author Laurens van den Bercken
 */
public class SubWizard extends JFrame implements ActionListener{
    
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private final int nrOfPlayers;
    private final String name;
    JTextField otherPlayers[] = new JTextField[10];
    
    public SubWizard (String name, int nrOfPlayers) throws FileNotFoundException, UnsupportedEncodingException {
        super();
        this.name = name;
        this.nrOfPlayers = nrOfPlayers;
        setSize(WIDTH, HEIGHT);
        setTitle("Enter names");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter name of other players.");
	for (int i = 0; i < nrOfPlayers; i++) {
                otherPlayers[i] = new JTextField("Enter name", 30);
		add(otherPlayers[i]);
	}
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(this);
        cancel.addActionListener(this);
        add(ok);
        add(cancel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String buttonString = e.getActionCommand();
        
        if(buttonString.equals("Exit")) {
            System.exit(0);
        }
        else if (buttonString.equals("Ok")) {
            ArrayList<String> playerNames = new ArrayList<>();
            for(int i = 0; i < nrOfPlayers; i++) {
                playerNames.add(otherPlayers[i].getText());
            }
            try {
                Main.createTeam(name, playerNames, nrOfPlayers);
                Main.startLogging();
                dispose();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("Unexpected event.");
        }
    }
    
}
