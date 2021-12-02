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
package com.idsmanager.main.service.dto.loginpage;

import com.idsmanager.main.domain.SPLoginConfig;
import com.idsmanager.main.service.dto.AbstractDto;

/**
 * @author mh
 * @date 2018/8/9
 */
public class SPLoginDto extends AbstractDto {
    private static final long serialVersionUID = -4657491264753116977L;

    private String spLoginUrl;

    private String spName;

    public SPLoginDto() {
    }

    public SPLoginDto(SPLoginConfig config) {
        super(config);
        this.spLoginUrl = config.spLoginUrl();
        this.spName = config.spName();
    }


    public String getSpLoginUrl() {
        return spLoginUrl;
    }

    public void setSpLoginUrl(String spLoginUrl) {
        this.spLoginUrl = spLoginUrl;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }
}