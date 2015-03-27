package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;

import entropia.Item;

public class Storage {

	public static HashMap<String, Item> ALLITEMS;

	public Storage() {
		ALLITEMS = new HashMap<String, Item>();
	}

	public static void writeItem(Item i) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				Paths.ITEMLISTPATH2, true)));
		out.println(i.getName());
		out.println(i.getValue());
		out.close();
	}

	public void readItemList() throws NumberFormatException, IOException {
		FileReader freader = new FileReader(Paths.ITEMLISTPATH2);
		BufferedReader breader = new BufferedReader(freader);
		String inputLine;
		while ((inputLine = breader.readLine()) != null) {
			String itemname = inputLine;
			String itemvalue = breader.readLine();
			ALLITEMS.put(
					itemname,
					new Item(itemname, BigDecimal.valueOf(Double
							.parseDouble(itemvalue))));
		}
		breader.close();
	}	
}
