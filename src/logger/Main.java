package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import util.Paths;
import util.Storage;
import entropedia.Player;
import entropedia.Team;

public class Main {

	public static String huntDate;

	private static Team team;
	private static Scanner scanner;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		setDate();
		new File(huntDate).mkdir();
		System.out.println("Reading itemlist.");
		Storage s = new Storage();
		s.readItemList();
		System.out.println("Done reading itemlist.");
		createTeam();
		System.out.println("Starting logging, type -1 to stop.");
		Logger logger = new Logger(Paths.LOGPATH, team);
		new Thread(logger).start();

		String input = scanner.next();
		while (!input.equals("-1")) {
			input = scanner.next();
		}
		scanner.close();
		logger.stopLogging();

	}

	private static void setDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmm");
		Calendar cal = Calendar.getInstance();
		huntDate = dateFormat.format(cal.getTime());
	}

	private static void createTeam() throws FileNotFoundException,
			UnsupportedEncodingException {
		System.out.println("Enter number of players:");
		scanner = new Scanner(System.in);
		int nrOfPlayers = Integer.parseInt(scanner.next());
		team = new Team(nrOfPlayers);
		for (int i = 0; i < nrOfPlayers; i++) {
			System.out.println("Enter playername:");
			String playername = scanner.next();
			team.addPlayer(new Player(i, playername));
		}
		System.out.println("New team created:");
		team.printPlayerList();
		
	}
}
