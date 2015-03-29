package entropia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import util.Storage;

public class Player extends Observable {
	private String name;
	private HashMap<String, String> stats;
	private int totalShots, totalMiss, totalCritHits, totalDeaths, accuracy;
	private BigDecimal totalLoot, pedGainPerShot, totalDmgDealt, totalDmgTaken,
			totalPedShot;
	private ArrayList<Loot> loots;
	
	private Gun weapon;

	public Player(String name) {
		loots = new ArrayList<Loot>();
		stats = new HashMap<String, String>();
		totalShots = 0;
		totalMiss = 0;
		totalCritHits = 0;
		totalDeaths = 0;
		accuracy = 0;
		totalLoot = BigDecimal.valueOf(0);
		pedGainPerShot = BigDecimal.valueOf(0);
		totalDmgDealt = BigDecimal.valueOf(0);
		totalDmgTaken = BigDecimal.valueOf(0);
		totalPedShot = BigDecimal.valueOf(0);
		this.name = name;
	}

	public void attack(boolean hit, double dmg) {
		totalShots++;
		int totalAmmoUsed = totalShots * weapon.getAmmoBurn();
		totalPedShot = BigDecimal.valueOf(totalAmmoUsed).multiply(Storage.AMMOPRICE);
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
		if(totalShots > 0) {
			pedGainPerShot = totalLoot.divide(BigDecimal.valueOf(totalShots), 5);
		}
		stats.put("Total shots",  ""+totalShots);
		stats.put("Total miss",  ""+totalMiss);
		stats.put("Total crits", ""+totalCritHits);
		stats.put("Total deaths", ""+totalDeaths);
		stats.put("Total dmg dealt", ""+totalDmgDealt);
		stats.put("Total dmg taken", ""+totalDmgTaken);
		stats.put("Accuracy", ""+accuracy);
		stats.put("Total PED shot", totalPedShot.toPlainString());
		stats.put("PED per shot", pedGainPerShot.toPlainString());
		return stats;
	}
	
	public Gun getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Gun weapon) {
		this.weapon = weapon;
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
