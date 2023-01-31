package com.idsmanager.demo.jwt.infrastructure.hibernate.queryhelper;

import com.idsmanager.demo.jwt.domain.SSOConfig;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Map;

/**
 * 2020/9/15
 *
 * @author Guilty_Crown
 */
public class SSOConfigQueryHelper extends AbstractQueryHelper<SSOConfig> {

    private final Map<String, Object> map;

    public SSOConfigQueryHelper(Session session, Map<String, Object> map) {
        super(session);
        this.map = map;
        addNameFilter();
    }

    private void addNameFilter() {
        String name = (String) map.get("name");
        if (StringUtils.isNotBlank(name)) {
            addFilter(new ParameterFilter() {
                @Override
                public void setParameter(Query query) {
                    query.setParameter("name", "%" + name + "%");
                }

                @Override
                public String getSubHql() {
                    return " and nc.name like :name";
                }
            });
        }
    }

    @Override
    public String getResultHql() {
        return "select nc from SSOConfig nc where nc.archived = false ";
    }

    @Override
    public String getAmountHql() {
        return "select count(nc.id) from SSOConfig nc where nc.archived = false ";
    }
}
