package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;

import entropia.Gun;
import entropia.Item;
import entropia.Material;

public class Storage {

	public static HashMap<String, Material> ALLMATERIALS;
	public static HashMap<String, Gun> ALLGUNS;
	public static HashMap<String, Item> ALLITEMS;
	
	public static final BigDecimal AMMOPRICE = BigDecimal.valueOf(0.0001);
	public static final String GUNTYPE = "Gun";
	public static final String MATERIALTYPE = "Material";
	public static final String UNKNOWNTYPE = "Unknown";

	public Storage() {
		ALLMATERIALS = new HashMap<String, Material>();
		ALLGUNS = new HashMap<String, Gun>();
		ALLITEMS = new HashMap<String, Item>();
	}

	public static void writeMaterial(Material i) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				Paths.ITEMLISTPATH, true)));
		out.println("material;" + i.getName() + ";" + i.getValue());
		out.close();
	}

	public void readItemList() throws NumberFormatException, IOException {
		FileReader freader = new FileReader(Paths.ITEMLISTPATH);
		BufferedReader breader = new BufferedReader(freader);
		String inputLine;
		while ((inputLine = breader.readLine()) != null) {
			String[] properties = inputLine.split(";");
			switch (properties[0]) {
			case "gun":
				addGun(properties);
				break;
			case "material":
				addMaterial(properties);
				break;
			}
		}
		breader.close();
	}

	private void addMaterial(String[] properties) {
		Material m = new Material(properties[1], BigDecimal.valueOf(Double
				.parseDouble(properties[2])));
		ALLMATERIALS.put(properties[1], m);
		ALLITEMS.put(properties[1], m);

	}

	private void addGun(String[] properties) {
		Gun g = new Gun(properties[1], Integer.parseInt(properties[2]),
				ALLMATERIALS.get(properties[3]));
		ALLGUNS.put(properties[1], g);
		ALLITEMS.put(properties[1], g);
	}

}
