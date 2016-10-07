package me.dong.model.repository;

import me.dong.model.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoty extends JpaRepository<Product, Long>{

    Page<Product> findByOrderByCreatedAtDesc(Pageable pageable);
}
