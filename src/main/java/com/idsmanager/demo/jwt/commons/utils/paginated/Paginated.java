package com.idsmanager.demo.jwt.commons.utils.paginated;

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface Paginated<T> {

    List<T> getList();

    int getPageNumber();

    int getPerPageSize();

    long getTotalSize();

    String getSortName();

    PaginatedSort getSort();

    long getTotalPages();
}