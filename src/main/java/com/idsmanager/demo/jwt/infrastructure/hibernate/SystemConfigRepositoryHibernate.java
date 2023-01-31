package com.idsmanager.demo.jwt.infrastructure.hibernate;

import com.idsmanager.demo.jwt.domain.SystemConfig;
import com.idsmanager.demo.jwt.domain.SystemConfigRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统配置
 *
 * @author Guilty_Crown
 */
@Repository("systemConfigRepositoryHibernate")
public class SystemConfigRepositoryHibernate extends AbstractRepositoryHibernate<SystemConfig> implements SystemConfigRepository {
    @Override
    public void save(SystemConfig systemConfig) {
        saveOrUpdate(systemConfig);
    }

    @Override
    public List<SystemConfig> findSystemConfig() {
        return findAll(SystemConfig.class, false);
    }
}
