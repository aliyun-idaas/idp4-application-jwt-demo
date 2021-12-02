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
package com.idsmanager.main.service.business.loginpage;

import com.idsmanager.main.domain.LoginPageTheme;
import com.idsmanager.main.service.dto.loginpage.LoginPageThemeDto;

/**
 * @author zy
 * @date 2018/8/9
 */
public class LoginPageThemeGenerator {
    private LoginPageThemeDto pageThemeDto;

    public LoginPageThemeGenerator(LoginPageThemeDto pageThemeDto) {
        this.pageThemeDto = pageThemeDto;
    }

    public LoginPageTheme generate() {
        LoginPageTheme pageTheme = new LoginPageTheme();
        pageTheme.backgroundColor(pageThemeDto.getBackgroundColor());
        pageTheme.fontColor(pageThemeDto.getFontColor());
        pageTheme.formColor(pageThemeDto.getFormColor());
        pageTheme.name(pageThemeDto.getName());
        pageTheme.buttonColor(pageThemeDto.getButtonColor());
        return pageTheme;
    }
}