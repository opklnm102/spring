package me.dong.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.dong.model.domain.Order;
import me.dong.model.domain.PayMethod;
import me.dong.model.vo.RequestVO;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderRequestVO extends RequestVO{

    @JsonProperty
    private String recipientName;

    @JsonProperty
    private String deliveryAddress;

    @JsonProperty
    private String recipientTel;

    @JsonProperty
    private PayMethod payMethod;

    @JsonProperty
    private Double orderPrice;

    @JsonProperty
    private List<Data> orderList;

    @Getter
    @Setter
    @NoArgsConstructor
    @RequiredArgsConstructor
    public static class Data implements Serializable{

        @NonNull
        private Long productId;

        @NonNull
        private Integer orderCount;
    }

    public Order toOrderEntity(){
        Order order = new Order();
        order.setRecipientName(this.recipientName);
        order.setDeliveryAddress(this.deliveryAddress);
        order.setRecipientTel(this.recipientTel);
        order.setPayMethod(this.payMethod);
        order.setOrderPrice(this.orderPrice);
        return order;
    }
}
