package me.dong.model.repository;

import me.dong.model.domain.Order;
import me.dong.model.domain.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

    Page<Order> getOrders(Long userId, Pageable pageable);
    Page<OrderProduct> getOrderProducts(Long userId, Pageable pageable);
}