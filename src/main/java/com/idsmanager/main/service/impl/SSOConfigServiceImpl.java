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
package com.idsmanager.main.service.impl;

import com.idsmanager.main.domain.SSOConfig;
import com.idsmanager.main.domain.SSOConfigRepository;
import com.idsmanager.main.service.SSOConfigService;
import com.idsmanager.main.service.business.sso.*;
import com.idsmanager.main.service.dto.sso.SSOConfigDto;
import com.idsmanager.main.service.dto.sso.SSOConfigPaginated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SSOConfigServiceImpl implements SSOConfigService {

    @Autowired
    private SSOConfigRepository ssoConfigRepository;

    @Override
    @Transactional(readOnly = true)
    public SSOConfigPaginated loadSSOConfigPaginated(SSOConfigPaginated paginated) {
        SSOConfigPaginatedLoader loader = new SSOConfigPaginatedLoader(paginated);
        return loader.load();
    }

    @Override
    @Transactional(readOnly = true)
    public SSOConfigDto loadSSOConfigDto() {
        SSOConfigDtoLoader loader = new SSOConfigDtoLoader();
        return loader.load();
    }

    @Override
    @Transactional()
    public void addSSOConfig(SSOConfigDto formDto) {
        SSOConfigSaver saver = new SSOConfigSaver(formDto);
        saver.save();
    }

    @Override
    @Transactional(readOnly = true)
    public SSOConfigDto findSSOConfigById(String id) {
        SSOConfig config = ssoConfigRepository.findSSOConfigById(id);
        return config != null ? new SSOConfigDto(config) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public SSOConfigDto loadSSOConfigDtoByUuid(String uuid) {
        SSOConfigDtoByUuidLoader loader = new SSOConfigDtoByUuidLoader(uuid);
        return loader.load();
    }

    @Override
    @Transactional()
    public void updateSSOConfig(SSOConfigDto formDto) {
        SSOConfigUpdater updater = new SSOConfigUpdater(formDto);
        updater.update();
    }

    @Override
    @Transactional()
    public void deleteSSOConfig(String uuid) {
        SSOConfigDeleter deleter = new SSOConfigDeleter(uuid);
        deleter.delete();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findSSOConfigByName(String name) {
        SSOConfig ssoConfig = ssoConfigRepository.findSSOConfigByName(name);
        return null == ssoConfig ? false : true;
    }
}
