package entropia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
	private String name;
	private HashMap<String, Double> stats;
	private int totalShots, totalMiss, totalCritHits, totalDeaths;
	private double totalDmgDealt, totalDmgTaken, accuracy;
	private BigDecimal totalLoot;
	private ArrayList<Loot> loots;

	public Player(int id, String name) {
		loots = new ArrayList<Loot>();
		totalLoot = BigDecimal.valueOf(0);
		stats = new HashMap<String, Double>();

		this.name = name;
	}

	public void attack(boolean hit, double dmg) {
		totalShots++;
		stats.put("totalShots", (double) totalShots);
		totalDmgDealt += dmg;
		if (!hit) {
			totalMiss++;
			stats.put("totalMiss", (double) totalMiss);
		}
		accuracy = Math.round(100 - totalMiss / (float) totalShots * 100);
		stats.put("accuracy", (double) accuracy);
	}

	public void die() {
		totalDeaths++;
		stats.put("totalDeaths", (double) totalDeaths);
	}

	public void critHit(double dmg) {
		totalShots++;
		stats.put("totalShots", (double) totalShots);
		totalCritHits++;
		stats.put("totalCritHits", (double) totalShots);
		totalDmgDealt += dmg;
	}

	public void takeHit(double dmg) {
		totalDmgTaken += dmg;
	}

	public double getTotalDmgDealt() {
		return totalDmgDealt;
	}

	public double getTotalDmgTaken() {
		return totalDmgTaken;
	}

	public int getTotalShots() {
		return totalShots;
	}

	public int getTotalDeaths() {
		return totalDeaths;
	}

	public int getTotalCritHits() {
		return totalCritHits;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public BigDecimal getTotalLoot() {
		return totalLoot;
	}

	public HashMap<String, Double> getAllStats() {
		stats.put("Total shots", (double) totalShots);
		stats.put("Total miss", (double) totalMiss);
		stats.put("Total crits", (double) totalCritHits);
		stats.put("Total deaths", (double) totalDeaths);
		stats.put("Total dmg dealt", totalDmgDealt);
		stats.put("Total dmg taken", totalDmgTaken);
		stats.put("Accuracy", accuracy);
		return stats;
	}
	
	public void printLootlist() {
		for (Loot l : loots) {
			System.out.println(l);
		}
	}

	public void addLoot(Loot l) {
		totalLoot = totalLoot.add(l.totalValue());
		loots.add(l);
	}

	public ArrayList<Loot> getLootList() {
		return loots;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
