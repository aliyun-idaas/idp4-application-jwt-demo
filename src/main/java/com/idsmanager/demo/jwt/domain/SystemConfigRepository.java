package com.idsmanager.demo.jwt.domain;

import java.util.List;

/**
 * Created by feng on 2016/4/5.
 */
public interface SystemConfigRepository {

    void save(SystemConfig systemConfig);

    List<SystemConfig> findSystemConfig();
}
