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
import entropia.Material;

public class Storage {

	public static HashMap<String, Material> ALLMATERIALS;
	public static HashMap<String, Gun> ALLGUNS;
	public static final BigDecimal AMMOPRICE = BigDecimal.valueOf(0.0001);
	
	public Storage() {
		ALLMATERIALS = new HashMap<String, Material>();
		ALLGUNS = new HashMap<String, Gun>();
	}

	public static void writeMaterial(Material i) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				Paths.ITEMLISTPATH, true)));
		out.println(i.getName() + ";" + i.getValue());
		out.close();
	}

	public void readItemList() throws NumberFormatException, IOException {
		FileReader freader = new FileReader(Paths.ITEMLISTPATH);
		BufferedReader breader = new BufferedReader(freader);
		String inputLine;
		while ((inputLine = breader.readLine()) != null) {
			String [] properties = inputLine.split(";");
			switch (properties[0]) {
				case "gun":
					handleGun(properties);
					break;
				case "material":
					handleMaterial(properties);
					break;	
			}
		}
		breader.close();
	}
	
	private void handleMaterial(String[] properties) {
		ALLMATERIALS.put(properties[1],
				new Material(properties[1],BigDecimal.valueOf(Double.parseDouble(properties[2]))));
		
	}

	private void handleGun(String[] properties) {
		ALLGUNS.put(properties[1],
				new Gun(properties[1], Integer.parseInt(properties[2]),
						ALLMATERIALS.get(properties[3])));
	}
	
	
}
