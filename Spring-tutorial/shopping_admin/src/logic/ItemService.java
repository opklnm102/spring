package logic;

import java.io.InputStream;
import java.util.List;

public interface ItemService {
	
	List<Item> getItemList();
	
	Item getItemByItemId(Integer itemId);
	
	List<Item> getItemByItemName(String itemName);
	
	void entryItem(Item item);
	
	void updateItem(Item item);
	
	void deleteItem(Item item);
	
	InputStream getPicture(Integer itemId);
}
