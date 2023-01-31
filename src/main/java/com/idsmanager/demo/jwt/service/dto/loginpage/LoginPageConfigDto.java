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
package com.idsmanager.demo.jwt.service.dto.loginpage;

import com.idsmanager.demo.jwt.domain.LoginPageConfig;
import com.idsmanager.demo.jwt.domain.SystemConfig;

import java.io.Serializable;

/**
 * @author zy
 * @date 2018/8/9
 */
public class LoginPageConfigDto implements Serializable {
    private static final long serialVersionUID = 7006378301628653764L;
    private String title = "JWT DEMO";

    private String copyright = SystemConfig.DEFAULT_COPYRIGHT;

    private String spSSoUrl;

    private String spLogoutUrl;

    public LoginPageConfigDto() {
    }

    public LoginPageConfigDto(LoginPageConfig config) {
        if (config != null) {
            this.title = config.title();
        }
    }


    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpSSoUrl() {
        return spSSoUrl;
    }

    public void setSpSSoUrl(String spSSoUrl) {
        this.spSSoUrl = spSSoUrl;
    }

    public String getSpLogoutUrl() {
        return spLogoutUrl;
    }

    public void setSpLogoutUrl(String spLogoutUrl) {
        this.spLogoutUrl = spLogoutUrl;
    }
}