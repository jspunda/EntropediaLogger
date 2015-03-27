package entropia;

import java.util.ArrayList;

public class Player {
	private String name;
	private int id;
	private ArrayList<Loot> loots;

	public Player(int id, String name) {
		loots = new ArrayList<Loot>();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void printLootlist() {
		for (Loot l : loots) {
			System.out.println(l);
		}
	}

	public void addLoot(Loot l) {
		loots.add(l);
	}

	public ArrayList<Loot> getLootList() {
		return loots;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return id + ". " + name;
	}
}
