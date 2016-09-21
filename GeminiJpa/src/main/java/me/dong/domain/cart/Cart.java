package me.dong.domain.cart;

import lombok.Getter;
import lombok.Setter;
import me.dong.domain.BaseEntity;
import me.dong.domain.user.User;

import javax.persistence.*;

/**
 * The type Cart. 장바구니
 */
@Setter
@Getter
@Entity
public class Cart extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private int itemQuantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
