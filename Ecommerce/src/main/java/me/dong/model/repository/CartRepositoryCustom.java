package me.dong.model.repository;

import me.dong.model.domain.CartProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Spring JPA에서 지원하지 않는 메소드 구현시 이용
 */
public interface CartRepositoryCustom {

    Page<CartProduct> getCartProducts(Long userId, Pageable pageable);
}
