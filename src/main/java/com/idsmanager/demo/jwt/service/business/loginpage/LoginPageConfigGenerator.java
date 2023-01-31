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
package com.idsmanager.demo.jwt.service.business.loginpage;

import com.idsmanager.demo.jwt.domain.LoginPageConfig;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageConfigDto;

/**
 * @author zy
 * @date 2018/8/9
 */
public class LoginPageConfigGenerator {
    private final LoginPageConfigDto configDto;

    public LoginPageConfigGenerator(LoginPageConfigDto configDto) {
        this.configDto = configDto;
    }

    public LoginPageConfig generate() {
        LoginPageConfig config = new LoginPageConfig();
        config.title(configDto.getTitle());
        return config;
    }
}