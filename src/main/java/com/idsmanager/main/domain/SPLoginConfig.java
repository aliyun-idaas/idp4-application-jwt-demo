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

import com.idsmanager.commons.domain.AbstractJpaDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author mh
 * @date 2018/8/8
 */
@Table(name = "sp_login_config")
@Entity
public class SPLoginConfig extends AbstractJpaDomain {
    private static final long serialVersionUID = 782176401252327233L;

    @Column(name = "sp_login_url")
    private String spLoginUrl;

    @Column(name = "sp_name")
    private String spName;

    public SPLoginConfig() {
    }

    public String spLoginUrl() {
        return spLoginUrl;
    }

    public SPLoginConfig spLoginUrl(String spLoginUrl) {
        this.spLoginUrl = spLoginUrl;
        return this;
    }

    public String spName() {
        return spName;
    }

    public SPLoginConfig spName(String spName) {
        this.spName = spName;
        return this;
    }
}