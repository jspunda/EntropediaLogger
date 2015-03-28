package entropia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Player extends Observable {
	private String name;
	private HashMap<String, String> stats;
	private int totalShots, totalMiss, totalCritHits, totalDeaths, accuracy;
	private BigDecimal totalLoot, PEDperShot, totalDmgDealt, totalDmgTaken;
	private ArrayList<Loot> loots;

	public Player(String name) {
		loots = new ArrayList<Loot>();
		stats = new HashMap<String, String>();
		totalLoot = BigDecimal.valueOf(0);
		PEDperShot = BigDecimal.valueOf(0);
		totalShots = 0;
		totalMiss = 0;
		totalCritHits = 0;
		totalDeaths = 0;
		totalDmgDealt = BigDecimal.valueOf(0);
		totalDmgTaken = BigDecimal.valueOf(0);
		accuracy = 0;
		this.name = name;
	}

	public void attack(boolean hit, double dmg) {
		totalShots++;
		totalDmgDealt = totalDmgDealt.add(BigDecimal.valueOf(dmg));
		if (!hit) {
			totalMiss++;
		}
		accuracy = 100 - Math.round(totalMiss/(float)totalShots*100);
	}

	public void die() {
		totalDeaths++;
	}

	public void critHit(double dmg) {
		totalShots++;
		totalCritHits++;
		totalDmgDealt = totalDmgDealt.add(BigDecimal.valueOf(dmg));
	}

	public void takeHit(double dmg) {
		totalDmgTaken = totalDmgTaken.add(BigDecimal.valueOf(dmg));
	}

	public BigDecimal getTotalDmgDealt() {
		return totalDmgDealt;
	}

	public BigDecimal getTotalDmgTaken() {
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

	public HashMap<String, String> getAllStats() {
		PEDperShot = totalLoot.divide(BigDecimal.valueOf(totalShots), 5);
		stats.put("Total shots",  ""+totalShots);
		stats.put("Total miss",  ""+totalMiss);
		stats.put("Total crits", ""+totalCritHits);
		stats.put("Total deaths", ""+totalDeaths);
		stats.put("Total dmg dealt", ""+totalDmgDealt);
		stats.put("Total dmg taken", ""+totalDmgTaken);
		stats.put("Accuracy", ""+accuracy);
		stats.put("PED per shot", PEDperShot.toPlainString());
		return stats;
	}
	
	public void changed() {
		setChanged();
		notifyObservers();
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
