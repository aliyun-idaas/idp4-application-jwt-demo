package com.idsmanager.demo.jwt.commons.utils.paginated;

/**
 * @author Shengzhao Li
 */
public enum PaginatedSort {
    ASC("asc"),
    DESC("desc");

    private final String label;

    PaginatedSort(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}