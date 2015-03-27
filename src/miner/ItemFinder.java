package miner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import util.Paths;
import entropia.Item;

public class ItemFinder {

	public static Item findItem(String itemname) throws IOException {
		BufferedReader r = getUrlStream(Paths.ENTROPEDIASEARCH + itemname);
		
		return new Item(null,null);
	}
	
	private static BufferedReader getUrlStream(String url) throws IOException {
		URL u = new URL(url);
		BufferedReader in = new BufferedReader(
		            new InputStreamReader(
		            u.openStream()));
		return in;
	}
	
}
