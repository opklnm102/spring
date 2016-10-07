package me.dong.model.service;

import me.dong.model.domain.*;
import me.dong.model.repository.OrderRepository;
import me.dong.model.repository.ProductRepositoty;
import me.dong.model.vo.OrderRequestVO;
import me.dong.model.vo.OrderRequestVO.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepositoty productRepositoty;

    public Page<Order> getOrders(Long userId, Pageable pageable){
        return orderRepository.getOrders(userId, pageable);
    }

    public Page<OrderProduct> getOrderProducts(Long userId, Pageable pageable){
        return orderRepository.getOrderProducts(userId, pageable);
    }

    public Page<Order> getOrders(Pageable pageable){
        return orderRepository.findByOrderByOrderDateDesc(pageable);
    }

    public Page<Order> getOrdersByOrderStatus(OrderStatus orderStatus, Pageable pageable){
        return orderRepository.findByOrderStatusOrderByOrderDateDesc(orderStatus, pageable);
    }

    public Order findOne(Long id){
        return orderRepository.findOne(id);
    }

    @Transactional
    public void order(User currentUser, OrderRequestVO requestVO){
        Order order = requestVO.toOrderEntity();
        order.setUser(currentUser);
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.ORDERED);
        order = orderRepository.save(order);

        List<Data> orderProducts = requestVO.getOrderList();
        for(Data data : orderProducts){
            Long productId = data.getProductId();
            Product product = productRepositoty.findOne(productId);
            OrderProduct orderProduct = new OrderProduct(order, product);
            orderProduct.setOrderCount(data.getOrderCount());
            order.getOrderProducts().add(orderProduct);
        }
    }

    @Transactional
    public void cancel(User currentUser, Long id){
        Order order = orderRepository.findOne(id);
        if(!order.getUser().equals(currentUser)){
            throw new RuntimeException("주문취소 권한이 없습니다.");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
    }

    @Transactional
    public Order save(Order order){
        return orderRepository.save(order);
    }
}
