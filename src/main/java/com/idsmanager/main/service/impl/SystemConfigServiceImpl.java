package com.idsmanager.main.service.impl;

import com.idsmanager.main.domain.SystemConfig;
import com.idsmanager.main.infrastructure.hibernate.SystemConfigRepositoryHibernate;
import com.idsmanager.main.service.LoginPageConfigService;
import com.idsmanager.main.service.SystemConfigService;
import com.idsmanager.main.service.dto.loginpage.LoginPageConfigDto;
import com.idsmanager.main.service.dto.loginpage.LoginPageDto;
import com.idsmanager.main.service.dto.loginpage.LoginPageThemeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author feng
 * @date 2016/4/5
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigRepositoryHibernate systemConfigRepository;


    @Autowired
    private LoginPageConfigService loginPageConfigService;

    @Override
    @Transactional(readOnly = true)
    public SystemConfig findSystemConfig() {
        List<SystemConfig> list = systemConfigRepository.findSystemConfig();
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional()
    public LoginPageDto loadLoginConfig() {
        LoginPageDto loginPageDto = new LoginPageDto();
        SystemConfig systemConfig = findSystemConfig();
        LoginPageConfigDto pageConfigDto = loginPageConfigService.findLoginPageConfig();
        loginPageDto.setSystemConfig(systemConfig);

        LoginPageThemeDto loginPageThemeDto = loginPageConfigService.findLoginPageThemeById(pageConfigDto.getThemeId());
        loginPageDto.setBackgroundColor(loginPageThemeDto.getBackgroundColor());
        loginPageDto.setFormColor(loginPageThemeDto.getFormColor());
        loginPageDto.setFontColor(loginPageThemeDto.getFontColor());
        loginPageDto.setTitle(pageConfigDto.getTitle());
        return loginPageDto;
    }
}
