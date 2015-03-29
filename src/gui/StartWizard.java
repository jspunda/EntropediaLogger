package gui;

import java.awt.Choice;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Laurens van den Bercken
 */
public class StartWizard extends JFrame implements ActionListener {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;

	private final JTextField nameField, gunField;
	private final Choice otherPlayers;
        private final JLabel nameLabel;
        private final JButton ok, cancel;
        
        private final MouseTextListener mouseListen = new MouseTextListener();

	public StartWizard() throws FileNotFoundException,
			UnsupportedEncodingException {
		super();
		setSize(WIDTH, HEIGHT);
		setTitle("Start Wizard");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		nameField = new JTextField("Enter your name.");
                nameField.addMouseListener(mouseListen);
                gunField = new JTextField("Enter your gun's exact name");
                gunField.addMouseListener(mouseListen);
		otherPlayers = new Choice();
                nameLabel = new JLabel("Select number of teammates:");
                for(int i = 1; i <= 10; i++) {
                    otherPlayers.add(""+i);
                }
		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
		ok.addActionListener(this);
		cancel.addActionListener(this);
		add(nameField);
                add(gunField);
                add(nameLabel);
		add(otherPlayers);
                add(ok);
                add(cancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonString = e.getActionCommand();

		if (buttonString.equals("Cancel")) {
			dispose();
		} else if (buttonString.equals("Ok")) {
			try {
				SubWizard sub = new SubWizard(nameField.getText(),
						Integer.parseInt(otherPlayers.getSelectedItem()));
                                sub.setLocationRelativeTo(this);
				sub.setVisible(true);
			} catch (FileNotFoundException | UnsupportedEncodingException ex) {
				ex.printStackTrace();
			}
			dispose();
		} else {
			System.out.println("Unexpected event.");
		}
        }
}
