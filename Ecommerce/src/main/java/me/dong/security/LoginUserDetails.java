package me.dong.security;

import lombok.Getter;
import me.dong.model.domain.User;

@Getter
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public LoginUserDetails(User user) {
        super(user.getUserName(), user.getPassword(), user.getAuthorities());
        this.user = user;

        System.out.println("login okok");
    }
}
