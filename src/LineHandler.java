import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineHandler {

	public static final String ITEMVALUEPATTERN = ".*class=\"IT\">(\\S*)";
	public static final String ITEMNAMEPATTERN = ".*class=\"PageSubject\">Material: (.*)<";
	public static final String TEAMPATTERN = "\\[Team\\] \\[\\w*\\] (.*) received (.*) \\((\\d*)\\)";

	private String playername;
	private Item item;
	private int quantity;

	public LineHandler() {}

	public Loot handleLine(String line, String pattern) throws IOException {
		Matcher m = match(line, pattern);
		if (m.find()) {
			playername = m.group(1);
			item = makeItem(m.group(2));
			quantity = Integer.parseInt(m.group(3));
			return new Loot(item, quantity, playername);
		} else {
			return null;
		}
	}

	private Item makeItem(String itemname) throws IOException {
		if (Main.ALLITEMS.containsKey(itemname)) {
			return Main.ALLITEMS.get(itemname);
		} else {
			return new Item("UNKNOWN (" + itemname + ")", new BigDecimal(0));
		}
	}

	private Matcher match(String line, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(line);
		return m;
	}

	
//	Will be used later in case you loot an item that is not in the known itemlist
//
//	private Item findItem(String itemname) throws IOException {
//		String name = itemname.replaceAll(" ", "%20");
//		BigDecimal value = new BigDecimal(0.0);
//		URL url = new URL(
//				"http://www.entropedia.info/Info.aspx?chart=Material&name="
//						+ name);
//		BufferedReader in = new BufferedReader(new InputStreamReader(
//				url.openStream()));
//		String inputLine;
//		while ((inputLine = in.readLine()) != null) {
//			if (inputLine.contains("<span title=''>Value:</span></td>")) {
//				Matcher m = match(inputLine, ITEMVALUEPATTERN);
//				if (m.find()) {
//					value = new BigDecimal(Double.parseDouble(m.group(1)));
//				} else {
//					System.out.println("no match onvalue");
//				}
//				break;
//			}
//		}
//		in.close();
//		Item i = new Item(itemname, value);
//		Main.ALLITEMS.put(itemname, i);
//		return i;
//	}
	
}
