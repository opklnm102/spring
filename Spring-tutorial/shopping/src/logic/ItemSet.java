package logic;

import java.io.Serializable;

public class ItemSet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Item item;
	
	private Integer quantity;

	public ItemSet(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public void addQuantity(Integer addQuantity){
		int adQuantityInt = addQuantity.intValue();
		int existQuantityInt = getQuantity().intValue();
		setQuantity(new Integer(adQuantityInt + existQuantityInt));
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
