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

import com.idsmanager.demo.jwt.domain.LoginPageTheme;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zy
 * @date 2018/8/9
 */
public class LoginPageThemeDto implements Serializable {
    private static final long serialVersionUID = -2401875612038116736L;
    private String thmemId;
    private String name;
    private String backgroundColor;
    private String fontColor;
    private String formColor;
    private String buttonColor;

    public LoginPageThemeDto() {
    }

    public LoginPageThemeDto(LoginPageTheme theme) {
        if (theme != null) {
            this.backgroundColor = theme.backgroundColor();
            this.fontColor = theme.fontColor();
            this.formColor = theme.formColor();
            this.name = theme.name();
            this.thmemId = theme.getUuid();
            this.buttonColor = theme.buttonColor();
        }
    }

    public static List<LoginPageThemeDto> toDtos(List<LoginPageTheme> themes) {
        return themes.stream().map(LoginPageThemeDto::new).collect(Collectors.toList());
    }

    public String getThmemId() {
        return thmemId;
    }

    public void setThmemId(String thmemId) {
        this.thmemId = thmemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFormColor() {
        return formColor;
    }

    public void setFormColor(String formColor) {
        this.formColor = formColor;
    }

    public String getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
    }
}