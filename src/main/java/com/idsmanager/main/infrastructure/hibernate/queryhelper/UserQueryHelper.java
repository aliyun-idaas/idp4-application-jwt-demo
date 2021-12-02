package com.idsmanager.main.infrastructure.hibernate.queryhelper;

import com.idsmanager.main.domain.security.User;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Map;

/**
 * 2020/9/15
 *
 * @author Guilty_Crown
 */
public class UserQueryHelper extends AbstractQueryHelper<User> {

    private final Map<String, Object> map;

    public UserQueryHelper(Session session, Map<String, Object> map) {
        super(session);
        this.map = map;
        addUsernameFilter();
    }

    private void addUsernameFilter() {
        final String username = (String) map.get("username");
        if (StringUtils.isNotBlank(username)) {
            addFilter(new ParameterFilter() {
                @Override
                public void setParameter(Query query) {
                    query.setParameter("username", "%" + username + "%");
                }

                @Override
                public String getSubHql() {
                    return " and nc.username like :username ";
                }
            });
        }
    }


    @Override
    public String getResultHql() {
        return "select nc from User nc where nc.archived = false";
    }

    @Override
    public String getAmountHql() {
        return "select count(nc.id) from User nc where nc.archived = false";
    }
}
