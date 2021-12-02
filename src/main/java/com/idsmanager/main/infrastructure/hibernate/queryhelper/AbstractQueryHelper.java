package com.idsmanager.main.infrastructure.hibernate.queryhelper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Guilty_Crown
 */
public abstract class AbstractQueryHelper<T> implements QueryHelper<T> {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractQueryHelper.class);
    private List<Filter> filters = new ArrayList();
    private List<SortCriterionFilter> sortCriterionFilters = new ArrayList();
    protected Session session;

    protected AbstractQueryHelper() {
    }

    protected AbstractQueryHelper(Session session) {
        this.session = session;
    }

    public void addFilter(Filter filter) {
        if (filter != null) {
            this.filters.add(filter);
        }
    }

    public void addSortCriterionFilter(SortCriterionFilter sortCriterionFilter) {
        if (sortCriterionFilter != null) {
            this.sortCriterionFilters.add(sortCriterionFilter);
        }
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public String getSubHql() {
        StringBuilder subHql = new StringBuilder();
        List<Filter> filters = this.getFilters();
        Iterator iterator = filters.iterator();

        while (iterator.hasNext()) {
            Filter filter = (Filter) iterator.next();
            subHql.append(filter.getSubHql());
        }
        return subHql.toString();
    }

    public String getSortHql() {
        StringBuilder sortHql = new StringBuilder();
        if (!this.sortCriterionFilters.isEmpty()) {
            sortHql.append(" order by ");
            int lastFilterIndex = this.sortCriterionFilters.size() - 1;
            Iterator iterator = this.sortCriterionFilters.iterator();

            while (iterator.hasNext()) {
                SortCriterionFilter sortCriterionFilter = (SortCriterionFilter) iterator.next();
                sortHql.append(sortCriterionFilter.getSubHql());
                int currentIndex = this.sortCriterionFilters.indexOf(sortCriterionFilter);
                if (currentIndex != lastFilterIndex) {
                    sortHql.append(",");
                }
            }
        }
        return sortHql.toString();
    }


    @Override
    public abstract String getResultHql();

    public int getStartPosition() {
        return 0;
    }

    public int getItemsAmountPerPage() {
        return 10;
    }

    public String getAmountHql() {
        throw new UnsupportedOperationException("Not yet implemented,please override it");
    }

    public Long getAmount() {
        String amountHql = this.getAmountHql() + this.getSubHql();
        LOG.debug("Amount hql: {}", amountHql);
        Query query = this.createQuery(amountHql);
        return (Long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getResults() {
        String resultHql = this.getResultHql() + this.getSubHql() + this.getSortHql();
        LOG.debug("Result hql: {}", resultHql);
        Query query = this.createQuery(resultHql);
        int amountPerPage = this.getItemsAmountPerPage();
        if (amountPerPage == 0) {
            return query.list();
        } else {
            int startPosition = this.getStartPosition();
            return query.setMaxResults(amountPerPage).setFirstResult(startPosition).list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T uniqueResult() {
        String resultHql = this.getResultHql() + this.getSubHql();
        LOG.debug("Result hql: {}", resultHql);
        Query query = this.createQuery(resultHql);
        return (T) query.uniqueResult();
    }

    protected Query createQuery(String resultHql) {
        Query query = this.session.createQuery(resultHql);
        List<Filter> filters = this.getFilters();
        Iterator iterator = filters.iterator();

        while (iterator.hasNext()) {
            Filter filter = (Filter) iterator.next();
            if (filter instanceof ParameterFilter) {
                ParameterFilter parameterFilter = (ParameterFilter) filter;
                parameterFilter.setParameter(query);
            }
        }
        return query;
    }
}
