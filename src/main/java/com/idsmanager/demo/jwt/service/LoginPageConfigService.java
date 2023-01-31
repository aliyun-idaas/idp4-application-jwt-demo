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

import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageConfigDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageThemeDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.SPLoginDto;

import java.util.List;

/**
 * Created by zy on 2018/8/9.
 */

public interface LoginPageConfigService {
    void saveLoginPageConfig(LoginPageConfigDto configDto);

    LoginPageConfigDto findLoginPageConfig();

    void saveLoginPageTheme(LoginPageThemeDto themeDto);

    List<LoginPageThemeDto> findAllLoginPageTheme();

    LoginPageThemeDto findLoginPageThemeById(String id);

    List<SPLoginDto> findSPLoginDto();

    void saveSPLoginConfig(SPLoginDto dto);

    void updateSPLoginConfig(SPLoginDto formDto);

    void deleteSPLoginConfig(String uuid);

    SPLoginDto findSPLoginConfigByuuid(String uuid);

    LoginPageDto loadLoginConfig();

}