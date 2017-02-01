package me.dong.web;

/**
 * 데이터를 담을 클래스
 * 메소드 인자가 많아지면 클래스로 분리
 * <form>의 name과 setter의 이름이 같아야 스프링에서 넣어준다
 */
public class User {

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
