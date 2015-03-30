package gui;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import entropia.Player;
import entropia.Team;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Laurens van den Bercken
 */
public class MainWindow extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
        private final JButton start, finish, exit;
	private final JLabel totalLoot, myTotalLoot, myShotsFired, myDmgDealt,
			myDmgTaken, myAcc, myPPS, myTotalPedShot, team1, team2, team3,
			team4, team5, team6, team7;
	
	private final JLabel[] teamLabels;

	public MainWindow() {
		super();
		setSize(WIDTH, HEIGHT);
		setTitle("Entropia Logger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Listener listen = new Listener();
		start = new JButton("Start");
		start.addActionListener(listen);
		finish = new JButton("Finish");
		finish.addActionListener(listen);
		exit = new JButton("Exit");
		exit.addActionListener(listen);
		JLabel loot = new JLabel("Team stats");
		loot.setFont(new Font("Tahoma", Font.PLAIN, 22));
		teamLabels = new JLabel[7];
		totalLoot = new JLabel("Total loot:");
		
		JLabel lblNewLabel = new JLabel("My stats");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		myTotalLoot = new JLabel("Total loot:");
		
		myShotsFired = new JLabel("Shots fired:");
		
		myDmgDealt = new JLabel("Total dmg dealt:");
		
		myDmgTaken = new JLabel("Total dmg taken:");
		
		myAcc = new JLabel("Accuracy:");
		
		myPPS = new JLabel("PED gain / shot:");
		
		myTotalPedShot = new JLabel("Total PED shot:");
		
		team1 = new JLabel("Not present (loot):");
		teamLabels[0] = team1;
		team2 = new JLabel("Not present (loot):");
		teamLabels[1] = team2;
		team3 = new JLabel("Not present (loot):");
		teamLabels[2] = team3;
		team4 = new JLabel("Not present (loot):");
		teamLabels[3] = team4;
		team5 = new JLabel("Not present (loot):");
		teamLabels[4] = team5;
		team6 = new JLabel("Not present (loot):");
		teamLabels[5] = team6;
		team7 = new JLabel("Not present (loot):");
		teamLabels[6] = team7;
		
		for (JLabel l : teamLabels) {
			l.setVisible(false);
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(482, Short.MAX_VALUE)
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(start)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(finish)
					.addGap(107))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(team7, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(team2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(loot, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addComponent(totalLoot, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
									.addComponent(team1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(team3, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(team4, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(team5, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(team6, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(myTotalPedShot, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(myPPS, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(myAcc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(myDmgTaken, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.LEADING)
								.addComponent(myTotalLoot, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(myDmgDealt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(myShotsFired, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap(72, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(loot)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(totalLoot)
						.addComponent(myTotalLoot))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(myShotsFired)
						.addComponent(team1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(myDmgDealt)
						.addComponent(team2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(myDmgTaken)
						.addComponent(team3))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(myAcc)
						.addComponent(team4))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(myPPS)
						.addComponent(team5))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(myTotalPedShot)
						.addComponent(team6))
					.addGap(18)
					.addComponent(team7)
					.addGap(123)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(exit)
						.addComponent(start)
						.addComponent(finish))
					.addGap(62))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}
	
	public JLabel [] getTeamLabels() {
		return teamLabels;
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		if (o.getClass().equals(Team.class)) {
			Team t = (Team) o;
			String total = t.getTotalLoot().toString();
			totalLoot.setText("Total loot: " + total + " PED");
			HashMap<String, Player> players = t.getPlayers();
			for (String key : players.keySet()) {
				Player p = players.get(key);
				teamLabels[p.getId()].setText(p.getName() + " (loot): " + p.getTotalLoot().toPlainString() + " PED");
			}
		} else {
			HashMap<String, String> stats = ((Player) o).getAllStats();
			myTotalLoot.setText("Total loot: " + ((Player) o).getTotalLoot() + " PED");
			myShotsFired.setText("Shots fired: " + stats.get("Total shots"));
			myDmgDealt.setText("Total dmg dealt: " + stats.get("Total dmg dealt"));
			myDmgTaken.setText("Total dmg taken: " + stats.get("Total dmg taken"));
			myAcc.setText("Accuracy: " + stats.get("Accuracy") + "%");
			myPPS.setText("PED gain / shot: " + stats.get("PED per shot") + " PED");
			myTotalPedShot.setText("Total PED shot: " + stats.get("Total PED shot") + " PED");
		}

	}
}
