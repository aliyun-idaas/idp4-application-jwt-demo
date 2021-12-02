package com.idsmanager.commons.utils.paginated;

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface PaginatedLoader<T> {

    List<T> loadList();

    long loadTotalSize();

}