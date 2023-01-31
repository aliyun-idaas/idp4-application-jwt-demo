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
 * 登录界面配色方案
 *
 * @author zy
 * @date 2018/8/8
 */
@Table(name = "login_page_theme")
@Entity
public class LoginPageTheme extends AbstractJpaDomain {
    private static final long serialVersionUID = 3837751668730917532L;

    @Column(name = "name")
    private String name;

    @Column(name = "back_ground_color")
    private String backgroundColor;

    @Column(name = "form_color")
    private String formColor;

    @Column(name = "font_color")
    private String fontColor;

    @Column(name = "button_color")
    private String buttonColor;

    public LoginPageTheme() {
    }

    public String name() {
        return name;
    }

    public LoginPageTheme name(String name) {
        this.name = name;
        return this;
    }

    public String backgroundColor() {
        return backgroundColor;
    }

    public LoginPageTheme backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String formColor() {
        return formColor;
    }

    public LoginPageTheme formColor(String formColor) {
        this.formColor = formColor;
        return this;
    }

    public String fontColor() {
        return fontColor;
    }

    public LoginPageTheme fontColor(String fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public String buttonColor() {
        return buttonColor;
    }

    public LoginPageTheme buttonColor(String buttonColor) {
        this.buttonColor = buttonColor;
        return this;
    }
}