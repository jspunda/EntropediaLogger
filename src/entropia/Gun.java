package entropia;

import java.math.BigDecimal;

import util.Storage;

public class Gun extends Item {
	
	private double dmgMin, dmgMax;
	private Material ammoType;
	private int ammoBurn;
	
	public Gun (String name, int ammoburn, Material ammoType) {
		super(name, BigDecimal.valueOf(0), Storage.GUNTYPE);
		this.ammoBurn = ammoburn;
		this.ammoType = ammoType;
		dmgMin = 0;
		dmgMax = 0;
	}
	
	public void setAmmoBurn(int burn) {
		this.ammoBurn = burn;
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
		return ammoBurn;
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += "\nAmmo burn: " + ammoBurn + "\nAmmo type: " + ammoType.getName();
		return s;
	}
}

