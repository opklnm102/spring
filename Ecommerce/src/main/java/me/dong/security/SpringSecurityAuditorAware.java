package me.dong.security;

import me.dong.model.domain.User;
import org.springframework.data.domain.AuditorAware;


public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        User user = getCurrentUser();
        Long id = user == null ? null : user.getId();
        return id;
    }

    private User getCurrentUser() {
        User user = null;

        try {
            user = SecurityUtils.getCurrentUser();
        }catch (RuntimeException ex){
            // ignore
        }

        return user;
    }
}
