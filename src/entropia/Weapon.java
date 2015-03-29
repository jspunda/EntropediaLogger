package entropia;

public class Weapon {
	
	private double dmgMin, dmgMax;
	private int ammoburn;
	private String name;
	
	public Weapon (String name) {
		this.name = name;
	}
	
	public Weapon() {}
	
	public void setAmmoBurn(int burn) {
		this.ammoburn = burn;
	}
	
	public void setDmgRange(double min, double max) {
		dmgMin = min;
		dmgMax = max;
	}
	
	public double getMaxDmg() {
		return dmgMax;
	}
	
	public double getMinDmg() {
		return dmgMin;
	}
	
	public int getAmmoBurn() {
		return ammoburn;
	}
	
	public String getName() {
		return name;
	}
}

