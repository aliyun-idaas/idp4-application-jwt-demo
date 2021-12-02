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
package com.idsmanager.main.service.business.sso;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.main.domain.SSOConfig;
import com.idsmanager.main.domain.SSOConfigRepository;
import com.idsmanager.main.service.dto.sso.SSOConfigDto;
import com.idsmanager.main.service.dto.sso.SSOConfigPaginated;

import java.util.List;
import java.util.Map;

/**
 * @author Guilty_Crown
 */
public class SSOConfigPaginatedLoader {

    private transient SSOConfigRepository ssoConfigRepository = BeanProvider.getBean(SSOConfigRepository.class);

    private SSOConfigPaginated paginated;

    public SSOConfigPaginatedLoader(SSOConfigPaginated paginated) {
        this.paginated = paginated;
    }

    public SSOConfigPaginated load() {
        Map<String, Object> map = paginated.queryMap();
        return paginated.load(new PaginatedLoader<SSOConfigDto>() {
            @Override
            public List<SSOConfigDto> loadList() {
                List<SSOConfig> ssoConfigList = ssoConfigRepository.findSSOConfigListPaginated(map);
                return SSOConfigDto.toDtos(ssoConfigList);
            }

            @Override
            public long loadTotalSize() {
                return ssoConfigRepository.totalSSOConfigSize(map);
            }
        });
    }
}
