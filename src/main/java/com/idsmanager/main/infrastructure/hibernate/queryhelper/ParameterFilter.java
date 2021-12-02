package com.idsmanager.main.infrastructure.hibernate.queryhelper;

import org.hibernate.Query;

/**
 * @author Guilty_Crown
 */
public abstract class ParameterFilter implements Filter {

    public ParameterFilter() {

    }

    public abstract void setParameter(Query query);
}

