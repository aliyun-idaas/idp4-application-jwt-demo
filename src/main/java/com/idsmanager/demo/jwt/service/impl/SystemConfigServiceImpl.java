package com.idsmanager.demo.jwt.service.impl;

import com.idsmanager.demo.jwt.domain.SystemConfig;
import com.idsmanager.demo.jwt.infrastructure.hibernate.SystemConfigRepositoryHibernate;
import com.idsmanager.demo.jwt.service.SystemConfigService;
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


    @Override
    @Transactional()
    public String saveSystemConfig(SystemConfig systemConfig) {
        systemConfigRepository.save(systemConfig);
        return systemConfig.getUuid();
    }

    @Override
    @Transactional(readOnly = true)
    public SystemConfig findSystemConfig() {
        List<SystemConfig> list = systemConfigRepository.findSystemConfig();
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
