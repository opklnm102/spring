package me.dong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 데이터를 담을 클래스
 * 메소드 인자가 많아지면 클래스로 분리
 * <form>의 name과 setter의 이름이 같아야 스프링에서 넣어준다
 */
@Entity  // Table과 매핑 설정
public class User extends AbstractEntity {

    @Column(nullable = false, length = 20, unique = true)  // NotNull, Unique
    @JsonProperty
    private String userId;

    @JsonProperty
    private String name;

    @JsonIgnore
    private String password;

    @JsonProperty
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

    public String getUserId() {
        return userId;
    }

    public boolean matchId(Long newId) {
        if (newId == null) {
            return false;
        }
        return newId.equals(getId());
    }

    public boolean matchPassword(String newPassword) {
        if (newPassword == null) {
            return false;
        }
        return newPassword.equals(password);
    }

    public void update(User newUser) {
        this.password = newUser.password;
        this.name = newUser.name;
        this.email = newUser.email;
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString() + '\'' +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
