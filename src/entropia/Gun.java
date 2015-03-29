package entropia;

public class Gun {
	
	private double dmgMin, dmgMax;
	private Material ammoType;
	private int ammoburn;
	private String name;
	
	public Gun (String name, int ammoburn, Material ammoType) {
		this.name = name;
		this.ammoburn = ammoburn;
		this.ammoType = ammoType;
	}
	
	public void setAmmoBurn(int burn) {
		this.ammoburn = burn;
	}
	
	public void setAmmoType(Material ammo) {
		this.ammoType = ammo;
	}
	
	public void setDmgRange(double min, double max) {
		dmgMin = min;
		dmgMax = max;
	}
	
	public Material getAmmoType() {
		return ammoType;
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
	
	public String toString(){
		return name;
	}
}

