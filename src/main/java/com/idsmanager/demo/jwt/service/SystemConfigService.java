package com.idsmanager.demo.jwt.service;

import com.idsmanager.demo.jwt.domain.SystemConfig;

/**
 * @author feng
 * @date 2016/4/5
 */
public interface SystemConfigService {

    SystemConfig findSystemConfig();

    String saveSystemConfig(SystemConfig systemConfig);
}
