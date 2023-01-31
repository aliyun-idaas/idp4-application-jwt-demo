package com.idsmanager.demo.jwt.infrastructure.hibernate.queryhelper;

import java.util.List;

/**
 * @author Guilty_Crown
 */
public interface QueryHelper<T> {

    String getResultHql();

    List<T> getResults();

    T uniqueResult();
}

