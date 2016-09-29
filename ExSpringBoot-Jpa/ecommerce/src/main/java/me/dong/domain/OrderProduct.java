package me.dong.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
@Getter
@Setter
@ToString(exclude = {"order", "product"})
@NoArgsConstructor
public class OrderProduct {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @MapsId(value = "orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @Column(name = "order_count")
    private Integer orderCount;

    public OrderProduct(Order order, Product product){
        this.id.orderId = order.getId();
        this.id.productId = product.getId();
        this.order = order;
        this.product = product;
    }

    public static class Id implements Serializable{

        @Column(name = "order_id")
        private Long orderId;

        @Column(name = "product_id")
        private Long productId;
    }
}
