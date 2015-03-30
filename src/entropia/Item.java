package entropia;

import java.math.BigDecimal;

public class Item {
	
	protected String itemname;
	protected String type;
	protected BigDecimal ttvalue;
	
	public Item (String itemname, BigDecimal ttvalue, String type) {
		this.itemname = itemname;
		this.ttvalue = ttvalue;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return itemname;
	}
	
	public BigDecimal getValue() {
		return ttvalue;
	}
	
	public String toString() {
		return "Name: " + itemname + "\nType: " + type + "\nTTValue: " + ttvalue;
	}
}
