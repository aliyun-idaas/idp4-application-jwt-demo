package com.idsmanager.demo.jwt.domain.security;


import com.idsmanager.demo.jwt.commons.domain.AbstractJpaDomain;
import com.idsmanager.demo.jwt.commons.utils.PasswordHandler;
import com.idsmanager.demo.jwt.domain.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统账号
 *
 * @author Guilty_Crown
 */
@Entity
@Table(name = "sys_user")
public class User extends AbstractJpaDomain {

    private static final long serialVersionUID = -1958822224307828242L;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "login_times")
    private int loginTimes = 0;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "type_")
    @Enumerated(EnumType.STRING)
    private UserType type = UserType.SELF_BUILD;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "sys_user_privilege", joinColumns = @JoinColumn(name = "sys_user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "privilege")
    private Set<Privilege> privileges = new HashSet<>();

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String originalPass) {
        this.password = PasswordHandler.encryptPassword(originalPass);
    }


    public void setPrivileges(List<Privilege> privileges) {
        this.privileges.addAll(privileges);
    }

    public String password() {
        return this.password;
    }

    public String username() {
        return this.username;
    }


    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String email() {
        return email;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", uuid='" + uuid + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}
