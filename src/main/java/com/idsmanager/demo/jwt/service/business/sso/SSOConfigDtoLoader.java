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
package com.idsmanager.demo.jwt.service.business.sso;

import com.idsmanager.demo.jwt.commons.utils.UUIDGenerator;
import com.idsmanager.demo.jwt.infrastructure.JWTHolder;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigDto;

/**
 * @author Guilty_Crown
 */
public class SSOConfigDtoLoader {


    public SSOConfigDtoLoader() {
    }

    public SSOConfigDto load() {
        SSOConfigDto dto = new SSOConfigDto();
        String ssoUrlId = String.valueOf(UUIDGenerator.generateNumber());
        String ssoUrl = JWTHolder.getJwtHost() + "public/sso/" + ssoUrlId;
        dto.setSsoUrlId(ssoUrlId);
        dto.setSsoUrl(ssoUrl);
        return dto;
    }
}
