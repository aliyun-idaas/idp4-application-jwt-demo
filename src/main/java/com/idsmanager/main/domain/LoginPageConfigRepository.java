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
package com.idsmanager.main.domain;

import java.util.List;

/**
 * 登录页面配置信息操作
 * Created by zy on 2018/8/9.
 */

public interface LoginPageConfigRepository {
    void saveLoginPageConfig(LoginPageConfig config);

    List<LoginPageConfig> findLoginPageConfig();

    void saveSPLoginConfig(SPLoginConfig config);

    void updateSPLoginConfig(SPLoginConfig config);

    void deleteSPLoginConfig(String uuid);

    List<SPLoginConfig> findSPLoginConfig();

    SPLoginConfig findSPLoginConfigByUuid(String uuid);

    void saveLoginPageTheme(LoginPageTheme theme);

    List<LoginPageTheme> findAllLoginPageTheme();

    LoginPageTheme findLoginPageThemeById(String id);
}