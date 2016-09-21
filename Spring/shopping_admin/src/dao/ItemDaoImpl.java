package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import logic.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private LobHandler lobHandler;

	private SimpleJdbcTemplate template;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SELECT_ALL = "SELECT item_id, item_name, price, description FROM item";

	@Override
	public List<Item> findAll() {
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		return this.template.query(ItemDaoImpl.SELECT_ALL, mapper);
	}

	private static final String SELECT_BY_PRIMARY_KEY = "SELECT item_id, item_name, price, description FROM item WHERE item_id = ?";

	@Override
	public Item findByPrimaryKey(Integer itemId) {
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		return this.template.queryForObject(SELECT_BY_PRIMARY_KEY, mapper, itemId);
	}

	private static final String SELECT_BY_ITEM_NAME = "SELECT item_id, item_name, price, description FROM item WHERE item_name LIKE ?";

	@Override
	public List<Item> findByItemName(String itemName) {
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		return this.template.query(ItemDaoImpl.SELECT_BY_ITEM_NAME, mapper, itemName + "%");
	}

	private static final String INSERT = "INSERT INTO item(item_name, price, description, picture) values (?,?,?,?)";

	@Override
	public void create(final Item item) {
		this.jdbcTemplate.execute(INSERT, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {

			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator)
					throws SQLException, DataAccessException {
				int index = 0;
				ps.setString(++index, item.getItemName());
				ps.setInt(++index, item.getPrice().intValue());
				ps.setString(++index, item.getDescription());

				try {
					lobCreator.setBlobAsBytes(ps, ++index, item.getPicture().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static final String UPDATE = "UPDATE item SET item_name = ?, price = ?, description = ?, picture = ? WHERE item_id = ?";

	@Override
	public void update(Item item) {
		this.jdbcTemplate.execute(UPDATE, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {

			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator)
					throws SQLException, DataAccessException {
				int index = 0;
				ps.setString(++index, item.getItemName());
				ps.setInt(++index, item.getPrice().intValue());
				ps.setString(++index, item.getDescription());

				try {
					lobCreator.setBlobAsBytes(ps, ++index, item.getPicture().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				ps.setInt(++index, item.getItemId().intValue());
			}
		});
	}

	@Override
	public void delete(Item item) {
		this.template.update("DELETE FROM item where item_id = ?", item.getItemId());
	}

	@Override
	public InputStream getPicture(Integer itemId) {
		return this.template.queryForObject("SELECT picture FROM item WHERE item_id = ?", new RowMapper<InputStream>() {
			public InputStream mapRow(ResultSet rs, int i) throws SQLException {
				return lobHandler.getBlobAsBinaryStream(rs, "picture");
			}
		}, itemId);
	}
}
