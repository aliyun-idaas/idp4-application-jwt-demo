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
package com.idsmanager.demo.jwt.service;


import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigDto;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigPaginated;

/**
 * @author Guilty_Crown
 */
public interface SSOConfigService {

    SSOConfigPaginated loadSSOConfigPaginated(SSOConfigPaginated paginated);

    SSOConfigDto loadSSOConfigDto();

    void addSSOConfig(SSOConfigDto formDto);

    SSOConfigDto findSSOConfigById(String id);

    SSOConfigDto loadSSOConfigDtoByUuid(String uuid);

    void updateSSOConfig(SSOConfigDto formDto);

    void deleteSSOConfig(String uuid);

    boolean findSSOConfigByName(String name);
}
