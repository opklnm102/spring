package me.dong.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString(exclude = {"orders", "cart"})
@NoArgsConstructor
public class User extends AbstractEntity<Long> {

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

    /**
     * 사용자는 여러개의 권한을 가질 수 있도록 N:N 매핑
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities = new HashSet<>();

    public User(String name, String password, String userName, HashSet<Authority> authorities){
        this.name = name;
        this.password = password;
        this.userName = userName;
        this.authorities = authorities;
    }

    /**
     * 사용자로부터 권한 조회
     * @return 조회된 권한
     */
    public Set<GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        this.authorities.forEach(authority -> authorities.add(authority));
        return authorities;
    }
}
