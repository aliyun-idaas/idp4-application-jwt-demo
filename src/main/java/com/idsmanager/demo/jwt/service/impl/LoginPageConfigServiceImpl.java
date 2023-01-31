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
package com.idsmanager.demo.jwt.service.impl;

import com.idsmanager.demo.jwt.domain.*;
import com.idsmanager.demo.jwt.service.LoginPageConfigService;
import com.idsmanager.demo.jwt.service.SystemConfigService;
import com.idsmanager.demo.jwt.service.business.loginpage.LoginPageConfigGenerator;
import com.idsmanager.demo.jwt.service.business.loginpage.LoginPageThemeGenerator;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageConfigDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageThemeDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.SPLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2018/8/9.
 */
@Service("loginPageConfig")
public class LoginPageConfigServiceImpl implements LoginPageConfigService {

    @Autowired
    private LoginPageConfigRepository configRepository;

    @Autowired
    private SystemConfigService configService;


    @Override
    @Transactional()
    public void saveLoginPageConfig(LoginPageConfigDto configDto) {
        List<LoginPageConfig> pageConfigs = configRepository.findLoginPageConfig();
        LoginPageConfig config;
        if (pageConfigs.size() != 0) {
            config = pageConfigs.get(0);
            config.title(configDto.getTitle());
        } else {
            LoginPageConfigGenerator configGenerator = new LoginPageConfigGenerator(configDto);
            config = configGenerator.generate();
        }
        SystemConfig systemConfig = configService.findSystemConfig();
        if (systemConfig == null) {
            systemConfig = new SystemConfig();
        }
        systemConfig.setSpSsoUrl(configDto.getSpSSoUrl());
        systemConfig.setSpLogoutUrl(configDto.getSpLogoutUrl());
        configService.saveSystemConfig(systemConfig);
        configRepository.saveLoginPageConfig(config);
    }

    @Override
    @Transactional()
    public LoginPageConfigDto findLoginPageConfig() {
        List<LoginPageConfig> configs = configRepository.findLoginPageConfig();
        LoginPageConfig config;
        if (configs.size() != 0) {
            config = configs.get(0);
        } else {
            config = new LoginPageConfig();
        }
        LoginPageConfigDto configDto = new LoginPageConfigDto(config);
        SystemConfig systemConfig = configService.findSystemConfig();
        if (systemConfig != null) {
            configDto.setCopyright(systemConfig.getCopyright());
            configDto.setSpSSoUrl(systemConfig.getSpSsoUrl());
            configDto.setSpLogoutUrl(systemConfig.getSpLogoutUrl());
        } else {
            configDto.setCopyright(SystemConfig.DEFAULT_COPYRIGHT);
        }
        return configDto;
    }

    @Override
    @Transactional()
    public void saveLoginPageTheme(LoginPageThemeDto themeDto) {
        LoginPageThemeGenerator themeGenerator = new LoginPageThemeGenerator(themeDto);
        LoginPageTheme theme = themeGenerator.generate();
        configRepository.saveLoginPageTheme(theme);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoginPageThemeDto> findAllLoginPageTheme() {
        List<LoginPageTheme> themes = configRepository.findAllLoginPageTheme();
        return LoginPageThemeDto.toDtos(themes);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginPageThemeDto findLoginPageThemeById(String id) {
        LoginPageTheme theme = configRepository.findLoginPageThemeById(id);
        return new LoginPageThemeDto(theme);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SPLoginDto> findSPLoginDto() {
        List<SPLoginConfig> list = configRepository.findSPLoginConfig();
        return converToSPLoginDtos(list);
    }

    private List<SPLoginDto> converToSPLoginDtos(List<SPLoginConfig> list) {
        List<SPLoginDto> dtos = new ArrayList<>();
        for (SPLoginConfig config : list) {
            SPLoginDto dto = new SPLoginDto(config);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    @Transactional()
    public void saveSPLoginConfig(SPLoginDto dto) {
        SPLoginConfig config = new SPLoginConfig();
        config.spLoginUrl(dto.getSpLoginUrl());
        config.spName(dto.getSpName());
        configRepository.saveSPLoginConfig(config);
    }

    @Override
    @Transactional()
    public void updateSPLoginConfig(SPLoginDto formDto) {
        SPLoginConfig config = configRepository.findSPLoginConfigByUuid(formDto.getUuid());
        config.spLoginUrl(formDto.getSpLoginUrl());
        config.spName(formDto.getSpName());
        configRepository.updateSPLoginConfig(config);
    }

    @Override
    @Transactional()
    public void deleteSPLoginConfig(String uuid) {
        configRepository.deleteSPLoginConfig(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public SPLoginDto findSPLoginConfigByuuid(String uuid) {
        SPLoginConfig config = configRepository.findSPLoginConfigByUuid(uuid);
        return new SPLoginDto(config);
    }

    @Override
    @Transactional()
    public LoginPageDto loadLoginConfig() {
        LoginPageDto loginPageDto = new LoginPageDto();
        SystemConfig systemConfig = configService.findSystemConfig();
        LoginPageConfigDto pageConfigDto = findLoginPageConfig();
        loginPageDto.setSystemConfig(systemConfig);

        loginPageDto.setTitle(pageConfigDto.getTitle());
        return loginPageDto;
    }
}