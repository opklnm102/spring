package me.dong.repository;


import me.dong.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findOne(Long id);

    Product save(Product product);

    void delete(Long id);
}
