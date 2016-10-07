package me.dong.model.service;

import me.dong.model.domain.Cart;
import me.dong.model.domain.CartProduct;
import me.dong.model.domain.Product;
import me.dong.model.repository.CartRepository;
import me.dong.model.repository.ProductRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepositoty productRepositoty;

    public Page<CartProduct> getCartProducts(Long userId, Pageable pageable){
        return cartRepository.getCartProducts(userId, pageable);
    }

    @Transactional
    public void addProduct(Long userId, Long productId, Integer buyCount){
        Cart cart = cartRepository.findOne(userId);
        Product product = productRepositoty.findOne(productId);
        CartProduct cartProduct = new CartProduct(cart, product);
        cartProduct.setBuyCount(buyCount);
        cart.getCartProducts().add(cartProduct);
    }

    @Transactional
    public void deleteProduct(Long userId, Long productId){
        Cart cart = cartRepository.findOne(userId);
        Product product = productRepositoty.findOne(productId);
        CartProduct cartProduct = new CartProduct(cart, product);
        cart.getCartProducts().remove(cartProduct);
    }
}
