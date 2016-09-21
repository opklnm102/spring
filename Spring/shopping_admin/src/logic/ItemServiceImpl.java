package logic;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ItemDao;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> getItemList() {
		return this.itemDao.findAll();
	}

	@Override
	public Item getItemByItemId(Integer itemId) {
		return this.itemDao.findByPrimaryKey(itemId);
	}

	@Override
	public List<Item> getItemByItemName(String itemName) {
		return this.itemDao.findByItemName(itemName);
	}

	@Override
	public void entryItem(Item item) {
		this.itemDao.create(item);
	}

	@Override
	public void updateItem(Item item) {
		this.itemDao.update(item);		
	}

	@Override
	public void deleteItem(Item item) {
		this.itemDao.delete(item);
	}

	@Override
	public InputStream getPicture(Integer itemId) {
		return this.itemDao.getPicture(itemId);
	}

}
