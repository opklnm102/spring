package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ItemDao;

@Service
public class ItemCatalogImpl implements ItemCatalog {

	@Autowired
	private ItemDao itemDao;
	
	public void setItemDao(ItemDao itemDao){
		this.itemDao = itemDao;
	}
	
	@Override
	public List<Item> getItemList() {
		return this.itemDao.findAll();
	}

	@Override
	public Item getItemByItemId(Integer itemId) {
		return this.itemDao.findByPrimaryKey(itemId);
	}
	

}
