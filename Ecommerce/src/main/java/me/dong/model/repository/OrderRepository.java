package me.dong.model.repository;

import me.dong.model.domain.Order;
import me.dong.model.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    Page<Order> findByOrderByOrderDateDesc(Pageable pageable);

    Page<Order> findByOrderStatusOrderByOrderDateDesc(OrderStatus orderStatus, Pageable pageable);
}
