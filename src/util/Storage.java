package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import entropedia.Item;

public class Storage {

	public static HashMap<String, Item> ALLITEMS;

	public Storage() {
		ALLITEMS = new HashMap<String, Item>();
	}
	
	public void readItemList() throws NumberFormatException,
			IOException {
		FileReader freader = new FileReader(Paths.ITEMLISTPATH);
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
