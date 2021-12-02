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
import java.util.Map;

public interface SSOConfigRepository {

    List<SSOConfig> findSSOConfigListPaginated(Map<String, Object> map);

    long totalSSOConfigSize(Map<String, Object> map);

    void saveSSOConfig(SSOConfig ssoConfig);

    SSOConfig findSSOConfigById(String id);

    SSOConfig findSSOConfigByUuid(String uuid);

    void updateSSOConfig(SSOConfig ssoConfig);

    void deleteSSOConfig(String uuid);

    SSOConfig findSSOConfigByName(String name);
}
