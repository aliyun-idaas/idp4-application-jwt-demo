package com.idsmanager.demo.jwt.domain.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Guilty_Crown
 */
public enum Privilege {


    SYSTEM_ACCOUNT("系统账号"),
    USER_ACCOUNT("普通用户");


    private final String label;

    Privilege(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }

    public static List<Privilege> availablePrivileges() {
        return Arrays.asList(
                SYSTEM_ACCOUNT,
                USER_ACCOUNT

        );
    }

    public static List<Privilege> tenantPrivileges() {
        return Collections.singletonList(USER_ACCOUNT);
    }

}
