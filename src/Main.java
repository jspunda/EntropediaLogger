import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.math.BigDecimal;

public class Main {

	public static HashMap<String, Item> ALLITEMS;
	public static final String LOGPATH = "C:\\Users\\Jeftha\\Documents\\Entropia Universe\\chat.log";
	public static final String ITEMLISTPATH = "C:\\Users\\Jeftha\\Documents\\Entropia Universe\\EntropiaItemList.txt";
	public static String huntDate;

	private static Team team;
	private static Scanner scanner;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		ALLITEMS = new HashMap<String, Item>();
		setDate();
		new File(huntDate).mkdir();
		System.out.println("Reading itemlist.");
		readItemList();
		System.out.println("Done reading itemlist.");
		createTeam();
		System.out.println("Starting logging, type -1 to stop.");
		Logger logger = new Logger(LOGPATH, team);
		new Thread(logger).start();

		String input = scanner.next();
		while (!input.equals("-1")) {
			input = scanner.next();
		}
		scanner.close();
		logger.stopLogging();

	}

	public static void writeToFile(String filename, ArrayList<Loot> loots)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter w = new PrintWriter(
				Main.huntDate + "/" + filename + ".txt", "UTF-8");
		HashMap<String, BigDecimal[]> map = merge(loots);
		BigDecimal totalValue = BigDecimal.valueOf(0);
		for (String key : map.keySet()) {
			BigDecimal itemTotal = map.get(key)[1];
			totalValue = totalValue.add(itemTotal);
			w.printf(Locale.FRANCE, "%s;%d;%f%n", key,
					map.get(key)[0].intValue(), itemTotal);
		}
		w.printf(Locale.FRANCE, "%f%n", totalValue);
		w.close();
	}

	private static HashMap<String, BigDecimal[]> merge(ArrayList<Loot> loots) {
		HashMap<String, BigDecimal[]> merged = new HashMap<String, BigDecimal[]>();
		for (Loot l : loots) {
			String key = l.getItem().getName();
			BigDecimal[] temp = new BigDecimal[2];
			BigDecimal quantity = new BigDecimal(0);
			BigDecimal total = new BigDecimal(0);
			if (merged.containsKey(key)) {
				quantity = merged.get(key)[0].add(BigDecimal.valueOf(l
						.getQuantity()));
				total = merged.get(key)[1].add(l.totalValue());

			} else {
				quantity = BigDecimal.valueOf(l.getQuantity());
				total = l.totalValue();
			}
			temp[0] = quantity;
			temp[1] = total;
			merged.put(key, temp);
		}
		return merged;
	}

	private static void setDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmm");
		Calendar cal = Calendar.getInstance();
		huntDate = dateFormat.format(cal.getTime());
	}

	private static void readItemList() throws NumberFormatException,
			IOException {
		FileReader freader = new FileReader(ITEMLISTPATH);
		BufferedReader breader = new BufferedReader(freader);
		String inputLine;
		while ((inputLine = breader.readLine()) != null) {
			String itemname = inputLine;
			String itemvalue = breader.readLine();
			ALLITEMS.put(
					itemname,
					new Item(itemname, BigDecimal.valueOf(Double
							.parseDouble(itemvalue))));
		}
		breader.close();
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
