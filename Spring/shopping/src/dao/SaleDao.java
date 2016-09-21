package dao;

import logic.Sale;

public interface SaleDao {
	
	public void create(Sale sale);
	
	public Integer findMaxSaleId();

}
