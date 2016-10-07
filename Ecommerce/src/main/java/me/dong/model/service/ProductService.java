package me.dong.model.service;

import me.dong.model.domain.Product;
import me.dong.model.repository.ProductRepositoty;
import me.dong.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoty productRepositoty;

    @Autowired
    private UserRepository userRepository;

    public Page<Product> findProducts(Pageable pageable){
        return productRepositoty.findByOrderByCreatedAtDesc(pageable);
    }

    public Product findOne(Long id){
        return productRepositoty.findOne(id);
    }

    @Transactional
    public Product save(Product product){
        return productRepositoty.save(product);
    }

    @Transactional
    public void delete(Long id){
        productRepositoty.delete(id);
    }
}
