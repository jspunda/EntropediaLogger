import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Logger implements Runnable {
	private BufferedReader breader;
	private FileReader freader;
	private boolean logging = false;
	private Team hunt;
	private LineHandler linehandler;

	public Logger(String filename, Team team) throws FileNotFoundException {
		this.hunt = team;
		linehandler = new LineHandler();
		freader = new FileReader(filename);
		breader = new BufferedReader(freader);
	}

	private void startLogging() throws IOException, InterruptedException {
		logging = true;
		while (logging) {
			String ln = breader.readLine();
			if (ln == null) {
				Thread.sleep(1000);
			} else {
				Loot l = linehandler.handleLine(ln, LineHandler.TEAMPATTERN);
				if (l != null) {
					System.out.println(ln);
					hunt.addLoot(l);
				}
			}
		}
		System.out.println("Ending logging");
		breader.close();
	}

	private void readUntilEnd() throws IOException {
		while ((breader.readLine()) != null) {}
	}
	
	public void stopLogging() throws IOException {
		logging = false;
		hunt.finalize();
	}

	@Override
	public void run() {
		try {
			readUntilEnd();
			startLogging();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
