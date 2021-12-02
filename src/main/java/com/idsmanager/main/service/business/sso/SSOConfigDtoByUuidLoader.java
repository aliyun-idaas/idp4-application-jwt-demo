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
import com.idsmanager.main.service.dto.sso.SSOConfigDto;

/**
 * @author Guilty_Crown
 */
public class SSOConfigDtoByUuidLoader {

    private transient SSOConfigRepository ssoConfigRepository = BeanProvider.getBean(SSOConfigRepository.class);

    private String uuid;

    public SSOConfigDtoByUuidLoader(String uuid) {
        this.uuid = uuid;
    }

    public SSOConfigDto load() {
        SSOConfig config = ssoConfigRepository.findSSOConfigByUuid(uuid);
        return new SSOConfigDto(config);
    }
}
