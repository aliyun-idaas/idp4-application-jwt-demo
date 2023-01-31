package com.idsmanager.demo.jwt.domain.security;

import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {


    private static SecurityHolder securityHolder;

    public void setSecurityHolder(SecurityHolder securityHolder) {
        SecurityUtils.securityHolder = securityHolder;
    }

    public static UserDetails userDetails() {
        return securityHolder.userDetails();
    }

    public static String username() {
        return securityHolder != null ? securityHolder.username() : null;
    }

    public static String currentUserGuid() {
        final UserDetails userDetails = userDetails();
        if (userDetails instanceof IdsUserDetails) {
            return ((IdsUserDetails) userDetails).user().getUuid();
        }
        return null;
    }
}	
