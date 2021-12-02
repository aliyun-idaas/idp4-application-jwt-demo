
package com.idsmanager.main.service.dto.user;


import com.idsmanager.main.domain.security.Privilege;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2015/12/19
 *
 * @author Shengzhao Li
 */
public class UserFormDto implements Serializable {
    private static final long serialVersionUID = -8251954993624473451L;


    private String username;

    private String password;

    private String displayName;

    //邮箱 ，必填
    private String email;

    protected Set<Privilege> privileges = new HashSet<>();

    private List<String> ouUuid = new ArrayList<>();

    public UserFormDto() {
    }

    public List<Privilege> getAllPrivileges() {
        return Privilege.availablePrivileges();
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public List<String> getOuUuid() {
        return ouUuid;
    }

    public void setOuUuid(List<String> ouUuid) {
        this.ouUuid = ouUuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
