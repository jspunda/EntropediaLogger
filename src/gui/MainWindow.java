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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private final JLabel totalLoot, myTotalLoot, myShotsFired, myDmgDealt,
			myDmgTaken, myAcc, myPPS, myTotalPedShot;

	public MainWindow() {
		super();
		setSize(WIDTH, HEIGHT);
		setTitle("Entropia Logger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Listener listen = new Listener();
		JButton start = new JButton("Start");
		start.addActionListener(listen);
		JButton finish = new JButton("Finish");
		finish.addActionListener(listen);
		JButton exit = new JButton("Exit");
		exit.addActionListener(listen);
		JLabel loot = new JLabel("Team stats");
		loot.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		totalLoot = new JLabel("Total loot:");
		
		JLabel lblNewLabel = new JLabel("My stats");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		myTotalLoot = new JLabel("Total loot:");
		
		myShotsFired = new JLabel("Shots fired:");
		
		myDmgDealt = new JLabel("Total dmg dealt:");
		
		myDmgTaken = new JLabel("Total dmg taken:");
		
		myAcc = new JLabel("Accuracy:");
		
		myPPS = new JLabel("PED gain per shot:");
		
		myTotalPedShot = new JLabel("Total PED shot:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(298)
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(start)
					.addGap(6)
					.addComponent(finish)
					.addContainerGap(295, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(loot, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalLoot, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(start)
						.addComponent(exit)
						.addComponent(finish))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(loot)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(totalLoot)
						.addComponent(myTotalLoot))
					.addGap(18)
					.addComponent(myShotsFired)
					.addGap(18)
					.addComponent(myDmgDealt)
					.addGap(18)
					.addComponent(myDmgTaken)
					.addGap(18)
					.addComponent(myAcc)
					.addGap(18)
					.addComponent(myPPS)
					.addGap(18)
					.addComponent(myTotalPedShot)
					.addGap(240))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		if (o.getClass().equals(Team.class)) {
			String total = ((Team) o).getTotalLoot().toString();
			totalLoot.setText("Total loot: " + total + " PED");
		} else {
			HashMap<String, String> stats = ((Player) o).getAllStats();
			myTotalLoot.setText("Total loot: " + ((Player) o).getTotalLoot() + " PED");
			myShotsFired.setText("Shots fired: " + stats.get("Total shots"));
			myDmgDealt.setText("Total dmg dealt: " + stats.get("Total dmg dealt"));
			myDmgTaken.setText("Total dmg taken: " + stats.get("Total dmg taken"));
			myAcc.setText("Accuracy: " + stats.get("Accuracy") + "%");
			myPPS.setText("PED gain per shot: " + stats.get("PED per shot") + " PED");
			myTotalPedShot.setText("Total PED shot: " + stats.get("Total PED shot") + " PED");
		}

	}
}
