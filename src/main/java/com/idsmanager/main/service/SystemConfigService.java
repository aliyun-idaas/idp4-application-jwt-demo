package com.idsmanager.main.service;

import com.idsmanager.main.domain.SystemConfig;
import com.idsmanager.main.service.dto.loginpage.LoginPageDto;

/**
 * @author feng
 * @date 2016/4/5
 */
public interface SystemConfigService {

    SystemConfig findSystemConfig();

    LoginPageDto loadLoginConfig();
}
