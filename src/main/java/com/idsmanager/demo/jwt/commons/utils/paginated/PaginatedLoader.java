package com.idsmanager.demo.jwt.commons.utils.paginated;

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface PaginatedLoader<T> {

    List<T> loadList();

    long loadTotalSize();

}