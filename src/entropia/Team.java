package entropia;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import logger.Main;
import util.Writer;

public class Team {

	private HashMap<String, Player> players;
	private ArrayList<Loot> loots;
	private BigDecimal totalLoot;

	public Team(int nrOfPlayers) throws FileNotFoundException,
			UnsupportedEncodingException {
		players = new HashMap<String, Player>();
		loots = new ArrayList<Loot>();
		totalLoot = BigDecimal.valueOf(0);
	}

	public void addPlayer(Player p) {
	
		players.put(p.getName(), p);
	}

	public Player getPlayer(String name) {
		return players.get(name);
	}

	public BigDecimal getTotalLoot() {
		return totalLoot;
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void printPlayerList() {
		for (String key : players.keySet()) {
			System.out.printf("%s%n", players.get(key).getName());
		}
	}

	public void addLoot(Loot l) {
		loots.add(l);
		totalLoot = totalLoot.add(l.totalValue());
		if (players.containsKey(l.getLootedBy())) {
			players.get(l.getLootedBy()).addLoot(l);
		} else {
			System.out.println("Playername doesn't match.");
		}
	}

	public void finalize() throws FileNotFoundException,
			UnsupportedEncodingException {
		Writer.writeLootList("huntTotal", loots);
		for (String key : players.keySet()) {
			Writer.writeLootList(players.get(key).getName(), players.get(key)
					.getLootList());
		}
		Writer.writePersonalStats(players.get(Main.ME).getAllStats());
	}

}
