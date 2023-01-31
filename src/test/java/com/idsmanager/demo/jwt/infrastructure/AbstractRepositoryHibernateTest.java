package com.idsmanager.demo.jwt.infrastructure;


import com.idsmanager.demo.jwt.SpringContextTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shengzhao Li
 */
public abstract class AbstractRepositoryHibernateTest extends SpringContextTest {

    @Autowired
    private SessionFactory sessionFactory;


    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    protected void fullClean() {
        session().flush();
        session().clear();
    }
}