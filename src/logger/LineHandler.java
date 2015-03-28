package logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Patterns;
import util.Storage;
import entropia.Item;
import entropia.Loot;
import entropia.Player;
import entropia.Team;

public class LineHandler {

	private Team team;
	private Player me;

	public LineHandler(Team team) {
		this.team = team;
		me = this.team.getPlayer(Main.ME);
	}

	public void handleLine(String line) throws IOException {
		for (String pattern : Patterns.ALLGAMEPATTERNS) {
			Matcher m = match(line, pattern);
			if (m.find()) {
				// System.out.println(line);
				switch (pattern) {
				case Patterns.TEAMPATTERN:
					handleTeamLine(m);
					break;
				case Patterns.SHOTHITPATTERN:
					handleHitLine(m, true);
					break;
				case Patterns.SHOTCRITPATTERN:
					handleCritHitLine(m);
					break;
				case Patterns.SHOTMISSPATTERN:
				case Patterns.SHOTEVADEPATTERN:
					handleHitLine(m, false);
					break;
				case Patterns.DMGTAKENPATTERN:
					handleDmgTakenLine(m);
					break;
				case Patterns.DEATHPATTERN:
					handleDeathLine();
					break;
				}
				team.changed();
				me.changed();
			} else {
				// System.out.println("Ignoring, no match found.");
			}
		}
	}

	private void handleTeamLine(Matcher m) throws IOException {
		String playername = m.group(1);
		Item item = makeItem(m.group(2));
		int quantity = Integer.parseInt(m.group(3));
		team.addLoot(new Loot(item, quantity, playername));
	}

	private void handleCritHitLine(Matcher m) {
		me.critHit(Double.parseDouble(m.group(1)));
	}

	private void handleHitLine(Matcher m, boolean hit) {
		if (hit) {
			me.attack(true, Double.parseDouble(m.group(1)));
		} else {
			me.attack(false, 0);
		}
	}

	private void handleDmgTakenLine(Matcher m) {
		me.takeHit(Double.parseDouble(m.group(1)));
	}

	private void handleDeathLine() {
		me.die();
	}

	private Item makeItem(String itemname) throws IOException {
		if (Storage.ALLITEMS.containsKey(itemname)) {
			return Storage.ALLITEMS.get(itemname);
		} else {
			return new Item("UNKNOWN (" + itemname + ")", new BigDecimal(0));
		}
	}

	private Matcher match(String line, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(line);
		return m;
	}

	// Will be used later in case you loot an item that is not in the known
	// itemlist
	//
	// private Item findItem(String itemname) throws IOException {
	// String name = itemname.replaceAll(" ", "%20");
	// BigDecimal value = new BigDecimal(0.0);
	// URL url = new URL(
	// "http://www.entropedia.info/Info.aspx?chart=Material&name="
	// + name);
	// BufferedReader in = new BufferedReader(new InputStreamReader(
	// url.openStream()));
	// String inputLine;
	// while ((inputLine = in.readLine()) != null) {
	// if (inputLine.contains("<span title=''>Value:</span></td>")) {
	// Matcher m = match(inputLine, ITEMVALUEPATTERN);
	// if (m.find()) {
	// value = new BigDecimal(Double.parseDouble(m.group(1)));
	// } else {
	// System.out.println("no match onvalue");
	// }
	// break;
	// }
	// }
	// in.close();
	// Item i = new Item(itemname, value);
	// Main.ALLITEMS.put(itemname, i);
	// return i;
	// }

}
