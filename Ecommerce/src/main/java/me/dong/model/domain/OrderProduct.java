package me.dong.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
@Getter
@Setter
@ToString(exclude = {"order", "product"})
@NoArgsConstructor
public class OrderProduct extends AbstractEntity<OrderProduct.Id> {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @MapsId(value = "orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JsonProperty
    @JsonUnwrapped
    private Product product;

    @Column(name = "order_count")
    @JsonProperty
    private Integer orderCount;

    public OrderProduct(Order order, Product product){
        this.id.orderId = order.getId();
        this.id.productId = product.getId();
        this.order = order;
        this.product = product;
    }

    @Embeddable
    @Data
    @EqualsAndHashCode(callSuper = false, of = {"orderId", "productId"})
    public static class Id extends AbstractEntityId {

        @Column(name = "order_id")
        private Long orderId;

        @Column(name = "product_id")
        private Long productId;
    }
}
