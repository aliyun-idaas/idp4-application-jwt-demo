
package com.idsmanager.main.service.dto.user;


import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.main.domain.security.User;

import java.util.Map;

/**
 * 2015/12/19
 *
 * @author Shengzhao Li
 */
public class UserPaginated extends DefaultPaginated<User> {


    private String username;

    public UserPaginated() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("username", username);
        return map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
