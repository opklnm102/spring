package dao;

import java.util.List;

import logic.Item;

public interface ItemDao {
	
	public List<Item> findAll();
	
	public Item findByPrimaryKey(Integer itemId);

}
