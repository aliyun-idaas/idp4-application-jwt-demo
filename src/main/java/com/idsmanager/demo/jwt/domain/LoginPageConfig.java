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
 * 登录界面配置（标题、配色方案）
 *
 * @author zy
 * @date 2018/8/8
 */
@Table(name = "login_page_config")
@Entity
public class LoginPageConfig extends AbstractJpaDomain {
    private static final long serialVersionUID = 782176401252327533L;

    @Column(name = "title")
    private String title = "JWT DEMO";

    @Column(name = "theme_id")
    private String themeId;

    public LoginPageConfig() {
    }

    public String title() {
        return title;
    }

    public LoginPageConfig title(String title) {
        this.title = title;
        return this;
    }

    public String themeId() {
        return themeId;
    }

    public LoginPageConfig themeId(String themeId) {
        this.themeId = themeId;
        return this;
    }
}