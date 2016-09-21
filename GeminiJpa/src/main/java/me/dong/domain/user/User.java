package me.dong.domain.user;


import lombok.Getter;
import lombok.Setter;
import me.dong.domain.BaseEntity;
import me.dong.domain.cart.Cart;

import javax.persistence.*;
import java.util.List;

/**
 * The type User. 고객
 */
@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String phone;

    @Column
    private int totalCartQuantity = 0;

    @Column
    private int totalCartPrice = 0;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Cart> carts;

    /*
    Stter를 안만드는 이유
    JPA는 Transaction 안에서 Entity의 변경사항을 감지하여 UPDATE SQL을 생성
    즉, Setter가 Update 기능 수행
    이런 상황에서 무분별하게 Setter를 쓰게된다면 예측하지 못한 필드가 Update 될 때
    Stter를 일일이 체크해야 한다.
     */
    public void updateInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void addCart(Cart cart) {
        this.totalCartQuantity += cart.getItemQuantity();
        this.totalCartPrice += cart.getPrice();
        cart.setUser(this);
        carts.add(cart);
    }
}
