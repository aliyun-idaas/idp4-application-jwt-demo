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
package com.idsmanager.demo.jwt.infrastructure.hibernate;

import com.google.common.collect.ImmutableMap;
import com.idsmanager.demo.jwt.domain.SSOConfig;
import com.idsmanager.demo.jwt.domain.SSOConfigRepository;
import com.idsmanager.demo.jwt.infrastructure.hibernate.queryhelper.SSOConfigQueryHelper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SSOConfigRepositoryHibernate
 *
 * @author Guilty_Crown
 */
@Repository("ssoConfigRepositoryHibernate")
public class SSOConfigRepositoryHibernate extends AbstractRepositoryHibernate<SSOConfig> implements SSOConfigRepository {
    @Override
    public List<SSOConfig> findSSOConfigListPaginated(Map<String, Object> map) {
        SSOConfigQueryHelper queryHelper = new SSOConfigQueryHelper(session(), map);
        return queryHelper.getResults();
    }

    @Override
    public long totalSSOConfigSize(Map<String, Object> map) {
        SSOConfigQueryHelper queryHelper = new SSOConfigQueryHelper(session(), map);
        return queryHelper.getAmount();
    }

    @Override
    public void saveSSOConfig(SSOConfig ssoConfig) {
        saveOrUpdate(ssoConfig);
    }

    @Override
    public SSOConfig findSSOConfigById(String id) {
        return findOne("from SSOConfig nc where nc.archived = false and nc.ssoUrlId = :ssoUrlId ",
                ImmutableMap.of("ssoUrlId", id));
    }

    @Override
    public SSOConfig findSSOConfigByUuid(String uuid) {
        return findByUuid(SSOConfig.class, uuid);
    }

    @Override
    public void updateSSOConfig(SSOConfig ssoConfig) {

    }

    @Override
    public void deleteSSOConfig(String uuid) {
        deleteByUuid(SSOConfig.class, uuid);
    }

    @Override
    public SSOConfig findSSOConfigByName(String name) {
        return findOne("from SSOConfig nc where nc.archived = false and nc.name = :name",
                ImmutableMap.of("name", name));
    }
}
