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
package com.idsmanager.demo.jwt.service.dto.sso;

import com.idsmanager.demo.jwt.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.demo.jwt.commons.web.WebUtils;

import java.util.Map;

/**
 * @author Guilty_Crown
 */
public class SSOConfigPaginated extends DefaultPaginated<SSOConfigDto> {

    private String name;

    public SSOConfigPaginated() {
    }

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("name", WebUtils.paramFilter(name));
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
