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
package com.idsmanager.main.infrastructure.hibernate;

import com.idsmanager.main.domain.LoginPageConfig;
import com.idsmanager.main.domain.LoginPageConfigRepository;
import com.idsmanager.main.domain.LoginPageTheme;
import com.idsmanager.main.domain.SPLoginConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LoginPageConfigRepositoryHibernate-登录页面配置信息数据库实现
 *
 * @author Guilty_Crown
 */
@Repository("loginPageConfigRepositoryHibernate")
public class LoginPageConfigRepositoryHibernate extends AbstractRepositoryHibernate<LoginPageConfig> implements LoginPageConfigRepository {

    @Override
    public void saveLoginPageConfig(LoginPageConfig config) {
        saveOrUpdate(config);
    }

    @Override
    public List<LoginPageConfig> findLoginPageConfig() {
        return findAll(LoginPageConfig.class, false);
    }

    @Override
    public void saveLoginPageTheme(LoginPageTheme theme) {
        saveOrUpdate(theme);
    }

    @Override
    public List<LoginPageTheme> findAllLoginPageTheme() {
        return findAll(LoginPageTheme.class, false);
    }

    @Override
    public LoginPageTheme findLoginPageThemeById(String id) {
        return findByUuid(LoginPageTheme.class, id);
    }

    @Override
    public void saveSPLoginConfig(SPLoginConfig config) {
        saveOrUpdate(config);
    }

    @Override
    public List<SPLoginConfig> findSPLoginConfig() {
        return findAll(SPLoginConfig.class, false);
    }

    @Override
    public void deleteSPLoginConfig(String uuid) {
        deleteByUuid(SPLoginConfig.class, uuid);
    }

    @Override
    public void updateSPLoginConfig(SPLoginConfig config) {

    }

    @Override
    public SPLoginConfig findSPLoginConfigByUuid(String uuid) {
        return findByUuid(SPLoginConfig.class, uuid);
    }
}