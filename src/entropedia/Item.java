package entropedia;

import java.math.BigDecimal;


public class Item {
	private String itemname;
	private BigDecimal ttvalue;
	
	public Item (String itemname, BigDecimal ttvalue) {
		this.itemname = itemname;
		this.ttvalue = ttvalue;
	}
	
	public String getName() {
		return itemname;
	}
	
	public BigDecimal getValue(){
		return ttvalue;
	}
	
	public String toString() {
		return itemname + " with ttvalue of: " + ttvalue;
	}
}
