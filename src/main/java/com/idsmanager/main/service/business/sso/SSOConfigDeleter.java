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
package com.idsmanager.main.service.business.sso;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.main.domain.SSOConfig;
import com.idsmanager.main.domain.SSOConfigRepository;

/**
 * @author Guilty_Crown
 */
public class SSOConfigDeleter {

    private transient SSOConfigRepository ssoConfigRepository = BeanProvider.getBean(SSOConfigRepository.class);

    private String uuid;

    public SSOConfigDeleter(String uuid) {
        this.uuid = uuid;
    }

    public void delete() {
        SSOConfig ssoConfig = ssoConfigRepository.findSSOConfigByUuid(uuid);
        if (ssoConfig != null) {
            ssoConfigRepository.deleteSSOConfig(uuid);
        }
    }
}
