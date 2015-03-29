package gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import logger.Main;

/**
 * @author Laurens van den Bercken
 */
public class StartWizard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
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
		nameField = new JTextField("Enter your name");
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
                GroupLayout groupLayout = new GroupLayout(getContentPane());
                groupLayout.setHorizontalGroup(
                	groupLayout.createParallelGroup(Alignment.LEADING)
                		.addGroup(groupLayout.createSequentialGroup()
                			.addGap(37)
                			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                				.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                				.addComponent(gunField, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                				.addGroup(groupLayout.createSequentialGroup()
                					.addComponent(nameLabel)
                					.addGap(17)
                					.addComponent(otherPlayers, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                				.addGroup(groupLayout.createSequentialGroup()
                					.addComponent(ok)
                					.addGap(12)
                					.addComponent(cancel))))
                );
                groupLayout.setVerticalGroup(
                	groupLayout.createParallelGroup(Alignment.LEADING)
                		.addGroup(groupLayout.createSequentialGroup()
                			.addGap(11)
                			.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                			.addGap(11)
                			.addComponent(gunField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                			.addGap(18)
                			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                				.addGroup(groupLayout.createSequentialGroup()
                					.addGap(6)
                					.addComponent(nameLabel))
                				.addComponent(otherPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                			.addGap(11)
                			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                				.addComponent(ok)
                				.addComponent(cancel)))
                );
                getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonString = e.getActionCommand();

		if (buttonString.equals("Cancel")) {
			dispose();
		} else if (buttonString.equals("Ok")) {
			try {
				SubWizard sub = new SubWizard(Integer.parseInt(otherPlayers
						.getSelectedItem()));
				sub.setLocationRelativeTo(this);
				Main.ME = nameField.getText();
				Main.MYGUN = gunField.getText();
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
