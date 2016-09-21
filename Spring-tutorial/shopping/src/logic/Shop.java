package logic;

import java.util.List;

public interface Shop{ 
	
	public Cart getCart();
	
	public Integer calculateTotalAmount(List<ItemSet> itemList);
	
	public void checkout(User user, Cart cart);
	
	public List<Item> getItemList();
	
	public Item getItemByItemId(Integer itemId);
	
	public User getUserIdAndPassword(String userId, String password);
	
	public void entryUser(User user);

}
