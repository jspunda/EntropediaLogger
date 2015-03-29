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
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JButton;

public class Main {

	public static String ME;
	public static String huntDate;
	private static MainWindow window;
	
	private static Team team;
	private static Logger logger;

	public static void main(String[] args) throws IOException {
		window = new MainWindow();
                window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void start() throws NumberFormatException, IOException {
		setDate();
		new File(huntDate).mkdir();
		Storage s = new Storage();
		s.readItemList();
		StartWizard wizard = new StartWizard();
                wizard.setLocationRelativeTo(window);
		wizard.setVisible(true);
	}

	public static void stop() throws IOException {
		
		logger.stopLogging();
                PopUp.infoBox("Finished and saved log", "Finished", window);
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
		PopUp.infoBox("New team created.", "Succes", window);
	}

	public static void startLogging() throws FileNotFoundException {	
            window.observe(team);
		window.observe(team.getPlayer(ME));
                while(Paths.TESTLOGPATH == null) {
                    Paths.TESTLOGPATH = getFileName();
                    if(Paths.TESTLOGPATH == null) {
                        PopUp.infoBox("Error: Select log file to start logging", "Error", window);
                    }
                }
                PopUp.infoBox("Starting logging...\nDon't forget to press finish"
                        + " when you're done logging!", "Starting", window);
		logger = new Logger(Paths.TESTLOGPATH, team);
		new Thread(logger).start();
	}
        
        private static String getFileName() {
            Frame f = new Frame();
            FileDialog dialog = new FileDialog(f, "Select a log file (.log)", FileDialog.LOAD);
            dialog.setFile("*.log");
            dialog.setFilenameFilter(new FilenameFilter(){
                @Override public boolean accept(File dir, String name) {
                return (name.endsWith(".log"));
                }
            }
            );
            dialog.setVisible(true);
            String logDir = dialog.getDirectory();
            String logFile = dialog.getFile();
            f.dispose();
            return (logDir != null && logFile != null) ? logDir + System.getProperty("file.separator") + logFile : null;
        }
}
