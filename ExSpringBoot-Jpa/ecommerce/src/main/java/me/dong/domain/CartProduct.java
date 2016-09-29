package me.dong.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_product")
@Getter
@Setter
@ToString(exclude = {"cart", "product"})
@NoArgsConstructor  // JPA는 default constructor 필요
public class CartProduct {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @MapsId(value = "cartId")
    private Cart cart;

    @ManyToOne
    @MapsId(value = "productId")
    private Product product;

    public CartProduct(Cart cart, Product product){
        this.id.cartId = cart.getId();
        this.id.productId = product.getId();
        this.cart = cart;
        this.product = product;
    }

    @Embeddable
    @Data
    @EqualsAndHashCode(of = {"cartId", "productId"})
    public static class Id implements Serializable{

        @Column(name = "cart_id")
        private Long cartId;

        @Column(name = "product_id")
        private Long productId;
    }
}
