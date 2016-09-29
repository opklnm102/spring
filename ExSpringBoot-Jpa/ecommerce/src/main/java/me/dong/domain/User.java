package me.dong.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString(exclude = {"orders", "cart"})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("orderDate desc")
    private Set<Order> orders = new HashSet<>();

    @Column(length = 30)
    private String name;

    @Column(length = 50)
    private String userName;

    @Column(length = 255)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String mobile;

}
