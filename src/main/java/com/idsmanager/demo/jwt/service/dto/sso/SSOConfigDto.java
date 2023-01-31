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
package com.idsmanager.demo.jwt.service.dto.sso;

import com.idsmanager.demo.jwt.commons.utils.IdsBase64Utils;
import com.idsmanager.demo.jwt.domain.SSOConfig;
import com.idsmanager.demo.jwt.infrastructure.JWTHolder;
import com.idsmanager.demo.jwt.service.dto.AbstractDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guilty_Crown
 */
public class SSOConfigDto extends AbstractDto {

    private String name;

    private String oldName;

    private String publicKey;

    private String ssoUrl;

    private String ssoUrlId;

    public SSOConfigDto() {
    }

    public SSOConfigDto(SSOConfig ssoConfig) {
        super(ssoConfig);
        this.name = ssoConfig.name();
        this.publicKey = IdsBase64Utils.decrypt(ssoConfig.publicKey());
        this.ssoUrlId = ssoConfig.ssoUrlId();
        this.ssoUrl = JWTHolder.getJwtHost() + "/" + "public/sso/" + ssoConfig.ssoUrlId();
        this.oldName = ssoConfig.name();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSsoUrl() {
        return ssoUrl;
    }

    public void setSsoUrl(String ssoUrl) {
        this.ssoUrl = ssoUrl;
    }

    public String getSsoUrlId() {
        return ssoUrlId;
    }

    public void setSsoUrlId(String ssoUrlId) {
        this.ssoUrlId = ssoUrlId;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public static List<SSOConfigDto> toDtos(List<SSOConfig> ssoConfigList) {
        List<SSOConfigDto> ssoConfigDtos = new ArrayList<>(ssoConfigList.size());
        for (SSOConfig ssoConfig : ssoConfigList) {
            ssoConfigDtos.add(new SSOConfigDto(ssoConfig));
        }
        return ssoConfigDtos;
    }
}
