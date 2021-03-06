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
public class SubWizard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	private final int nrOfPlayers;
        // Please note that maximum number of teammates = 10
	JTextField otherPlayers[] = new JTextField[10];

	public SubWizard(int nrOfPlayers)
			throws FileNotFoundException, UnsupportedEncodingException {
		super();
		this.nrOfPlayers = nrOfPlayers;
		setSize(WIDTH, HEIGHT);
		setTitle("Enter names");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		new JLabel("Enter name of other players.");
		for (int i = 0; i < nrOfPlayers; i++) {
			otherPlayers[i] = new JTextField("Enter name.", 30);
                        otherPlayers[i].addMouseListener(new MouseTextListener());
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

		if (buttonString.equals("Cancel")) {
			dispose();
		} else if (buttonString.equals("Ok")) {
			ArrayList<String> playerNames = new ArrayList<>();
			for (int i = 0; i < nrOfPlayers; i++) {
				playerNames.add(otherPlayers[i].getText());
			}
			try {
				Main.createTeam(playerNames, nrOfPlayers);
				Main.startLogging();
				dispose();
			} catch (FileNotFoundException | UnsupportedEncodingException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Unexpected event.");
		}
	}

}
