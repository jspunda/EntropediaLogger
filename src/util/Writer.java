package util;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import logger.Main;
import entropia.Loot;

public class Writer {

	public static void writeLootList(String filename, ArrayList<Loot> loots)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter w = new PrintWriter(
				Main.huntDate + "/" + filename + ".txt", "UTF-8");
		HashMap<String, BigDecimal[]> map = merge(loots);
		BigDecimal totalValue = BigDecimal.valueOf(0);
		BigDecimal totalValueMinAmmo = BigDecimal.valueOf(0);
		for (String key : map.keySet()) {
			BigDecimal itemTotal = map.get(key)[1];
			totalValue = totalValue.add(itemTotal);
			if (!key.equals("Weapon Cells")) {
				totalValueMinAmmo = totalValueMinAmmo.add(itemTotal);
			}
			w.printf(Locale.FRANCE, "%s;%d;%s%n", key,
					map.get(key)[0].intValue(), itemTotal);
		}
		w.printf(Locale.FRANCE, "Total loot;%f%n", totalValue);
		w.printf(Locale.FRANCE, "Total loot (min Weapon Cells);%f%n", totalValueMinAmmo);
		w.close();
	}

	public static void writePersonalStats(HashMap<String, String> stats)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter w = new PrintWriter(Main.huntDate + "/myStats.txt", "UTF-8");
		for (String key : stats.keySet()) {
			w.printf(Locale.FRANCE, "%s;%s%n", key, stats.get(key));
		}
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
	
}
