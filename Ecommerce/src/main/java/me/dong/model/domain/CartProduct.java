package me.dong.model.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_product")
@Getter
@Setter
@ToString(exclude = {"cart", "product"})
@NoArgsConstructor  // JPA는 default constructor 필요
public class CartProduct extends AbstractEntity<CartProduct.Id> {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @MapsId(value = "cartId")
    private Cart cart;

    @ManyToOne
    @MapsId(value = "productId")
    @JsonProperty
    @JsonUnwrapped
    private Product product;

    @Column(name = "buy_count")
    @JsonProperty
    private Integer buyCount;

    public CartProduct(Cart cart, Product product){
        this.id.cartId = cart.getId();
        this.id.productId = product.getId();
        this.cart = cart;
        this.product = product;
    }

    @JsonProperty(value = "createdAt")
    public Long getCreatedTimestamp(){
        if(this.createdAt == null){
            return null;
        }
        return this.createdAt.getTime();
    }

    @Embeddable
    @Data
    @EqualsAndHashCode(callSuper = false, of = {"cartId", "productId"})
    public static class Id extends AbstractEntityId {

        @Column(name = "cart_id")
        private Long cartId;

        @Column(name = "product_id")
        private Long productId;
    }
}
