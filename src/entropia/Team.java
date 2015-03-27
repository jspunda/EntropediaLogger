package entropia;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import util.Writer;

public class Team {

	private Player[] players;
	private ArrayList<Loot> loots;

	public Team(int nrOfPlayers) throws FileNotFoundException,
			UnsupportedEncodingException {
		players = new Player[nrOfPlayers];
		loots = new ArrayList<Loot>();
	}

	public void addPlayer(Player p) {
		players[p.getId()] = p;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void printPlayerList() {
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i]);
		}
	}

	public void addLoot(Loot l) {
		loots.add(l);
		Player p = findPlayer(l.getLootedBy());
		if (p != null) {
			p.addLoot(l);
		} else {
			System.out.println("Playername doesn't match.");
		}
	}

	public void finalize() throws FileNotFoundException,
			UnsupportedEncodingException {
		Writer.writeToFile("huntTotal", loots);
		for (Player p : players) {
			Writer.writeToFile(p.getName(), p.getLootList());
		}
	}

	private Player findPlayer(String playername) {
		for (int i = 0; i < players.length; i++) {
			if (players[i].getName().equals(playername)) {
				return players[i];
			}
		}
		return null;
	}
}
