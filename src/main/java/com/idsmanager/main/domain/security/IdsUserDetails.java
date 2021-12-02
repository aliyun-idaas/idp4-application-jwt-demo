package com.idsmanager.main.domain.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * @author Guilty_Crown
 */
public class IdsUserDetails implements UserDetails {


    private static final long serialVersionUID = -2513888014140057691L;

    protected static final String ROLE_PREFIX = "ROLE_";

    protected User user;

    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public IdsUserDetails() {

    }

    public IdsUserDetails(User user) {
        this.user = user;
        initialAuthorities();
    }

    private void initialAuthorities() {
        final Set<Privilege> privileges = user.getPrivileges();
        for (Privilege privilege : privileges) {
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege));
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public User user() {
        return this.user;
    }

}
