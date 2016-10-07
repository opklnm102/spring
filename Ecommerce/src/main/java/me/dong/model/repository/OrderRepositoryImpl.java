package me.dong.model.repository;


import com.mysema.query.jpa.impl.JPAQuery;
import me.dong.model.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Order> getOrders(Long userId, Pageable pageable) {
        JPAQuery query = new JPAQuery(entityManager);
        QOrder order = QOrder.order;
        QUser user = QUser.user;

        query.from(order).join(order.user, user)
                .where(user.id.eq(userId))
                .orderBy(order.orderDate.desc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset());

        return new PageImpl<Order>(query.list(order), pageable, query.count());
    }

    @Override
    public Page<OrderProduct> getOrderProducts(Long userId, Pageable pageable) {
        JPAQuery query = new JPAQuery(entityManager);
        QOrderProduct orderProduct = QOrderProduct.orderProduct;
        QOrder order = QOrder.order;
        QProduct product = QProduct.product;

        query.from(orderProduct).join(orderProduct.order, order).join(orderProduct.product, product)
                .where(orderProduct.createdBy.eq(userId))
                .orderBy(orderProduct.createdAt.desc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset());

        return new PageImpl<OrderProduct>(query.list(orderProduct), pageable, query.count());
    }
}
