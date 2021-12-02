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

import com.idsmanager.commons.utils.IdsBase64Utils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.main.domain.SSOConfig;
import com.idsmanager.main.domain.SSOConfigRepository;
import com.idsmanager.main.service.dto.sso.SSOConfigDto;

/**
 * @author Guilty_Crown
 */
public class SSOConfigUpdater {

    private transient SSOConfigRepository ssoConfigRepository = BeanProvider.getBean(SSOConfigRepository.class);

    private SSOConfigDto formDto;

    public SSOConfigUpdater(SSOConfigDto formDto) {
        this.formDto = formDto;
    }

    public void update() {
        SSOConfig ssoConfig = ssoConfigRepository.findSSOConfigByUuid(formDto.getUuid());
        ssoConfig.name(formDto.getName());
        ssoConfig.publicKey(IdsBase64Utils.encrypt(formDto.getPublicKey()));
        ssoConfigRepository.updateSSOConfig(ssoConfig);
    }
}
