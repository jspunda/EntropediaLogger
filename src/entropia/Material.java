package entropia;

import java.math.BigDecimal;

import util.Storage;


public class Material extends Item{

	private BigDecimal ttvalue;
	
	public Material (String itemname, BigDecimal ttvalue) {
		super(itemname, ttvalue, Storage.MATERIALTYPE);
		this.ttvalue = ttvalue;
	}
	
	public BigDecimal getValue(){
		return ttvalue;
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += "\nTTValue: " + ttvalue;
		return s ;
	}
}
