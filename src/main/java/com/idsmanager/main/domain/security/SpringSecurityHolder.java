package com.idsmanager.main.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class SpringSecurityHolder implements SecurityHolder {

    public static ThreadLocal<SecurityContext> securityContextThreadLocal = new ThreadLocal<>();

    public static void setSecurityContext(SecurityContext securityContext) {
        Assert.notNull(securityContext);
        securityContextThreadLocal.set(securityContext);
    }

    public static SecurityContext getSecurityContext() {
        return securityContextThreadLocal.get();
    }


    @Override
    public UserDetails userDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        }
        return null;
    }

    @Override
    public String username() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof String) {
            return (String) principal;
        }
        return null;
    }

}
