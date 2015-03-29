package gui;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * @author Laurens van den Bercken
 */
public class StartWizard extends JFrame implements ActionListener {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;

	private final JTextField nameField;
	private final JTextField otherPlayers;

	public StartWizard() throws FileNotFoundException,
			UnsupportedEncodingException {
		super();
		setSize(WIDTH, HEIGHT);
		setTitle("Start Wizard");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		nameField = new JTextField("Enter your name.", 30);
		otherPlayers = new JTextField("Enter number of other players.", 30);
		JButton exit = new JButton("Ok");
		JButton start = new JButton("Cancel");
		exit.addActionListener(this);
		start.addActionListener(this);
		add(nameField);
		add(otherPlayers);
		add(exit);
		add(start);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonString = e.getActionCommand();

		if (buttonString.equals("Cancel")) {
			dispose();
		} else if (buttonString.equals("Ok")) {
			try {
				SubWizard sub = new SubWizard(nameField.getText(),
						Integer.parseInt(otherPlayers.getText()));
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
