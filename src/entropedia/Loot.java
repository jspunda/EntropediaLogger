package entropedia;

import java.math.BigDecimal;

public class Loot {

	private Item item;
	private int quantity;
	private String lootedBy;

	public Loot(Item item, int quantity, String lootedBy) {
		this.item = item;
		this.quantity = quantity;
		this.lootedBy = lootedBy;
	}

	public String getLootedBy() {
		return lootedBy;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal totalValue() {
		return item.getValue().multiply(new BigDecimal(quantity));
	}

	public String toString() {
		return lootedBy + " found " + quantity + " " + item + " Total loot value: " + totalValue();
	}

}
