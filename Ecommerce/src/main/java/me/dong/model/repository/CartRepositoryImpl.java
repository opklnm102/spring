package me.dong.model.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import me.dong.model.domain.CartProduct;
import me.dong.model.domain.QCart;
import me.dong.model.domain.QCartProduct;
import me.dong.model.domain.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CartRepositoryCustom 구현체
 */
public class CartRepositoryImpl implements CartRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<CartProduct> getCartProducts(Long userId, Pageable pageable) {
        JPAQuery query = new JPAQuery(entityManager);
        QCartProduct cartProduct = QCartProduct.cartProduct;
        QCart cart = QCart.cart;
        QUser user = QUser.user;

        query.from(cartProduct).join(cartProduct.cart, cart).join(cart.user, user)
                .where(user.id.eq(userId))
                .orderBy(cartProduct.createdAt.desc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset());

        return new PageImpl<CartProduct>(query.list(cartProduct), pageable, query.count());
    }
}
