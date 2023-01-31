package com.idsmanager.demo.jwt.infrastructure.hibernate;


import com.google.common.collect.ImmutableMap;
import com.idsmanager.demo.jwt.commons.domain.AbstractJpaDomain;
import com.idsmanager.demo.jwt.commons.domain.AbstractJpaRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Guilty_Crown
 */
public abstract class AbstractRepositoryHibernate<T> implements AbstractJpaRepository, InitializingBean {

    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    protected <T extends AbstractJpaDomain> List<T> find(String queryString, ImmutableMap<String, ?> paramsMap) {
        Query query = this.session().createQuery(queryString);
        if (paramsMap != null) {
            for (String key : paramsMap.keySet()) {
                query.setParameter(key, paramsMap.get(key));
            }
        }
        return query.list();
    }

    protected long count(String queryString, ImmutableMap<String, ?> paramsMap) {
        Query query = this.session().createQuery(queryString);
        if (paramsMap != null) {
            for (String key : paramsMap.keySet()) {
                query.setParameter(key, paramsMap.get(key));
            }
        }
        return (long) query.setMaxResults(1).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected <T extends AbstractJpaDomain> T findOne(String queryString, ImmutableMap<String, ?> paramsMap) {
        Query query = this.session().createQuery(queryString);
        if (paramsMap != null) {
            for (String key : paramsMap.keySet()) {
                query.setParameter(key, paramsMap.get(key));
            }
        }
        return (T) query.setMaxResults(1).uniqueResult();
    }

    @Override
    public <T extends AbstractJpaDomain> T findById(long id, Class<T> clazz) {
        List<T> list = this.find("from " + clazz.getSimpleName() + " do where do.id = :id", ImmutableMap.of("id", id));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T extends AbstractJpaDomain> T findByUuid(Class<T> clazz, String uuid) {
        if (null == uuid) {
            return null;
        }
        List<T> list = this.find("from " + clazz.getSimpleName() + " do where do.uuid = :uuid", ImmutableMap.of("uuid", uuid));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T extends AbstractJpaDomain> void saveOrUpdate(T JpaDomain) {
        this.session().saveOrUpdate(JpaDomain);
    }

    @Override
    public <T extends AbstractJpaDomain> void saveOrUpdateAll(Collection<T> JpaDomainList) {
        for (T JpaDomain : JpaDomainList) {
            saveOrUpdate(JpaDomain);
        }
    }

    @Override
    public <T extends AbstractJpaDomain> void delete(T JpaDomain) {
        this.session().delete(JpaDomain);
    }

    @Override
    public <T extends AbstractJpaDomain> void deleteByUuid(Class<T> clazz, String uuid) {
        T JpaDomain = this.findByUuid(clazz, uuid);
        this.delete(JpaDomain);
    }

    @Override
    public <T extends AbstractJpaDomain> void deleteAll(Collection<T> JpaDomainList) {
        for (T JpaDomain : JpaDomainList) {
            this.delete(JpaDomain);
        }
    }

    @Override
    public <T extends AbstractJpaDomain> List<T> findAll(Class<T> clazz, boolean archived) {
        return this.find("from " + clazz.getName() + " c where c.archived = :archived", ImmutableMap.of("archived", archived));
    }

    @Override
    public <T extends AbstractJpaDomain> List<T> findByUuids(Class<T> clazz, List<String> uuidList) {
        if (uuidList != null && !uuidList.isEmpty()) {
            Query query = this.session().createQuery("from " + clazz.getName() + " c where c.uuidList in (:uuidList)");
            query.setParameterList("uuidList", uuidList);
            return query.list();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.sessionFactory, "SessionFactory is required!");
    }

    protected Session session() {
        return this.sessionFactory.getCurrentSession();
    }
}
