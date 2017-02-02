package me.dong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 데이터를 담을 클래스
 * 메소드 인자가 많아지면 클래스로 분리
 * <form>의 name과 setter의 이름이 같아야 스프링에서 넣어준다
 */
@Entity  // Table과 매핑 설정
public class User {

    @Id  // PK 설정
    @GeneratedValue  // DB에서 자동으로 1씩 증가
    private Long id;

    @Column(nullable = false, length = 20, unique = true)  // NotNull, Unique
    private String userId;

    private String name;

    private String password;

    private String email;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void update(User newUser) {
        this.password = newUser.password;
        this.name = newUser.name;
        this.email = newUser.email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
