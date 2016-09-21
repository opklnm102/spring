package me.dong.domain.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
1. 유저는 장바구니에 새로운 상품을 추가할 수 있어야 한다.
2. 유저가 장바구니에 담았던 누적 수량, 금액을 저장하고 있어야 한다.
 */
@Service
public class CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void addCart(Long userId, Cart cart) {
//        userInfoUpdater.updateCartInfo(userId, cart);
        cartRepository.save(cart);
    }

}
