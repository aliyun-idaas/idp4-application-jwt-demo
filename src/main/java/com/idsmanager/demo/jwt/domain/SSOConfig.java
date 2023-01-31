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
package com.idsmanager.demo.jwt.domain;

import com.idsmanager.demo.jwt.commons.domain.AbstractJpaDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Guilty_Crown
 */
@Table(name = "sso_config")
@Entity
public class SSOConfig extends AbstractJpaDomain {

    @Column(name = "name")
    private String name;

    /**
     * sso路径的id,随机生成
     */
    @Column(name = "sso_url_id")
    private String ssoUrlId;

    @Column(name = "public_key", length = 2048)
    private String publicKey;

    public String name() {
        return name;
    }

    public SSOConfig name(String name) {
        this.name = name;
        return this;
    }

    public String ssoUrlId() {
        return ssoUrlId;
    }

    public SSOConfig ssoUrlId(String ssoUrlId) {
        this.ssoUrlId = ssoUrlId;
        return this;
    }

    public String publicKey() {
        return publicKey;
    }

    public SSOConfig publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }
}
