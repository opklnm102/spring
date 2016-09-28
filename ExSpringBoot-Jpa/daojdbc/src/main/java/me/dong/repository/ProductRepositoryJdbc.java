package me.dong.repository;


import me.dong.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProductRepositoryJdbc implements ProductRepository {

    private SimpleJdbcInsert simpleJdbcInsert;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = jdbcTemplate.query("SELECT id, name, price FROM product", productRowMapper);
        return products;
    }

    @Override
    public Product findOne(Long id) {
        return jdbcTemplate.queryForObject("SELECT id, name, price, FROM product WHERE id=?", productRowMapper, id);
    }

    @Override
    @Transactional(readOnly = false)
    public Product save(Product product) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(product);

        if(product.getId() == null){  // INSERT
            Number key = simpleJdbcInsert.executeAndReturnKey(param);
            product.setId(key.longValue());
            this.jdbcTemplate.update(
                    "INSERT INTO product (name, price) VALUES (?, ?)",
                    product.getName(), product.getPrice());
        }else{  // UPDATE
            this.jdbcTemplate.update(
                    "UPDATE product SET name= ?, price= ?, WHERE id= ?",
                    product.getName(), product.getPrice(), product.getId());
        }
        return product;
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update(
                "DELETE FROM product WHERE id= ?", id);
    }

    private static final RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            Long price = rs.getLong("price");
            return new Product(id, name, price);
        }
    };

    @PostConstruct
    private void init(){
        // INSERT SQL문을 자동으로 생성
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("product")
                .usingGeneratedKeyColumns("id");
    }
}
