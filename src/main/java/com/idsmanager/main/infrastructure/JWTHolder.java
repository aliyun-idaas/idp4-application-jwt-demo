/*
 * Copyright (c) 2016 BeiJing JZYT Technology Co. Ltd
 * www.idsmanager.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BeiJing JZYT Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with BeiJing JZYT Technology Co. Ltd.
 */
package com.idsmanager.main.infrastructure;

import org.springframework.beans.factory.InitializingBean;

/**
 * holder
 */
public class JWTHolder implements InitializingBean {

    public static final String ENCODING = "UTF-8";

    public static final String APP_NAME = "appName";

    public static final String THEME_COLOR = "themeColor";

    /**
     * 版权
     */
    public static final String COPYRIGHT = "copyright";

    /**
     * jwt-demo的主版本号
     */
    public static final String VERSION = "1.3.2";

    private static String jwtHost;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public static String getJwtHost() {
        return jwtHost;
    }

    public void setJwtHost(String jwtHost) {
        JWTHolder.jwtHost = jwtHost;
    }
}
