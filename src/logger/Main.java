package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import util.Paths;
import util.Storage;
import entropia.Player;
import entropia.Team;
import gui.*;
import java.util.ArrayList;

public class Main {

	public static String ME;
	public static String huntDate;
	private static MainWindow window;
	
	private static Team team;
	private static Logger logger;

	public static void main(String[] args) throws IOException {

		window = new MainWindow();
		window.setVisible(true);
	}

	public static void start() throws NumberFormatException, IOException {
		setDate();
		new File(huntDate).mkdir();
		PopUp.infoBox("Reading itemlist...", "Reading");
		Storage s = new Storage();
		s.readItemList();
		PopUp.infoBox("Done reading itemlist...", "Done");
		StartWizard wizard = new StartWizard();
		wizard.setVisible(true);
	}

	public static void stop() throws IOException {
		
		logger.stopLogging();
	}

	private static void setDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmm");
		Calendar cal = Calendar.getInstance();
		huntDate = dateFormat.format(cal.getTime());
	}

	public static void createTeam(String name, ArrayList<String> names,
			int nrOfPlayers) throws FileNotFoundException,
			UnsupportedEncodingException {
		team = new Team(nrOfPlayers + 1);
		ME = name;
		team.addPlayer(new Player(name));
		for (int i = 1; i <= nrOfPlayers; i++) {
			team.addPlayer(new Player(names.get(i - 1)));
		}
		PopUp.infoBox("New team created.", "Succes");
	}

	public static void startLogging() throws FileNotFoundException {
		window.observe(team);
		window.observe(team.getPlayer(ME));
		PopUp.infoBox("Starting logging, press Finish to stop.\nDon't"
				+ " forget to press Finish!", "Starting");
		logger = new Logger(Paths.TESTLOGPATH2, team);
		new Thread(logger).start();
	}
}
