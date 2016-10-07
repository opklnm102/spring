package me.dong.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "order")
@Getter
@Setter
@ToString(exclude = "orderProducts")
public class Order extends AbstractEntity<Long> {

    @Id
    @GeneratedValue
    @JsonProperty(value = "orderId")
    private Long id;

    @Column(name = "order_date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 12)
    @JsonProperty
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_method", length = 12)
    @JsonProperty
    private PayMethod payMethod;

    @Column(name = "order_price")
    @JsonProperty
    private Double orderPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    protected User user;

    @Column(name = "recipient_name", length = 30)
    @JsonProperty
    private String recipientName;  //제조사 이름

    @Column(name = "delivery_address", length = 255)
    @JsonProperty
    private String deliveryAddress;

    @Column(name = "recipient_tel", length = 15)
    @JsonProperty
    private String recipientTel;  //제조사 연락처

    @JsonProperty("orderDate")
    public Long getOrderDateTimestamp() {
        return this.orderDate.getTime();
    }

    @Transient
    public String getOrderProductNames() {
        if (CollectionUtils.isEmpty(this.orderProducts)) {
            return "";
        }
        List<String> names = this.orderProducts
                .stream()
                .map(op -> {
                    return op.getProduct().getName();
                })
                .collect(Collectors.toList());

        return StringUtils.join(names, ",");
    }
}
