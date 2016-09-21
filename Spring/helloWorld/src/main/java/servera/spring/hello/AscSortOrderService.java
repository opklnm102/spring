package servera.spring.hello;

import org.springframework.stereotype.Service;

@Service
public class AscSortOrderService implements SortOrderService{
	
	private String sortOrder = "DESC";

	@Override
	public String getSortOrder() {
		return sortOrder;
	}
}
