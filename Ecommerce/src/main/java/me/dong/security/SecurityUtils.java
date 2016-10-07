package me.dong.security;

import lombok.extern.apachecommons.CommonsLog;
import me.dong.model.domain.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@CommonsLog
public class SecurityUtils {

    private SecurityUtils() {
    }

    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() == null) {
            throw new AccessDeniedException("User not found in security session");
        }
        Authentication auth = securityContext.getAuthentication();
        AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
        User currentUser = null;
        boolean signupUser = trustResolver.isAnonymous(auth);
        if(auth != null && !signupUser){
            currentUser = getCurrentUser(auth);
        }
        return currentUser;
    }

    private static User getCurrentUser(Authentication auth){
        LoginUserDetails loginUserDetails = null;
        log.debug(auth);
        if(auth.getPrincipal() instanceof UserDetails){
            loginUserDetails = (LoginUserDetails) auth.getPrincipal();
        }else if(auth.getDetails() instanceof UserDetails){
            loginUserDetails = (LoginUserDetails) auth.getDetails();
        }else{
            throw new AccessDeniedException("User not properly authenticated");
        }
        return loginUserDetails.getUser();
    }
}
