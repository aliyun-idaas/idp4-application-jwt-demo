package com.idsmanager.demo.jwt.domain;

import com.idsmanager.demo.jwt.commons.domain.AbstractJpaDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author feng
 * @date 2016/4/5
 */
@Table(name = "system_config")
@Entity
public class SystemConfig extends AbstractJpaDomain {

    private static final long serialVersionUID = -3767988178102796485L;

    /**
     * 默认版权信息
     *
     * @since 1.3.1
     */
    public static final String DEFAULT_COPYRIGHT = "2014-2019 IDsManager.com";

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "public_key_id")
    private String publicKeyId;

    /**
     * SP SSO 地址 IDP/public/sp/sso/internetjwt1
     */
    @Column(name = "sp_sso_url")
    private String spSsoUrl;

    /**
     * SP 全局退出地址 IDP/public/sp/logout/{appId}
     */
    @Column(name = "sp_logout_url")
    private String spLogoutUrl;

    /**
     * 版权信息自定义
     *
     * @since 1.3.1
     */
    @Column(name = "copyright")
    private String copyright = DEFAULT_COPYRIGHT;

    public SystemConfig() {
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKeyId() {
        return publicKeyId;
    }

    public void setPublicKeyId(String publicKeyId) {
        this.publicKeyId = publicKeyId;
    }

    public String getSpSsoUrl() {
        return spSsoUrl;
    }

    public void setSpSsoUrl(String spSsoUrl) {
        this.spSsoUrl = spSsoUrl;
    }

    public String getSpLogoutUrl() {
        return spLogoutUrl;
    }

    public void setSpLogoutUrl(String spLogoutUrl) {
        this.spLogoutUrl = spLogoutUrl;
    }
}
