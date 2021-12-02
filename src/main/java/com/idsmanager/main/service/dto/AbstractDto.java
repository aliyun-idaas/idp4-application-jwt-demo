package com.idsmanager.main.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idsmanager.commons.domain.AbstractJpaDomain;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 2016/4/18
 *
 * @author Shengzhao Li
 */
public abstract class AbstractDto implements Serializable {
    private static final long serialVersionUID = 7353668104478205218L;


    protected String uuid;
    protected String createTime;


    public AbstractDto() {
    }

    public AbstractDto(AbstractJpaDomain domain) {
        this.uuid = domain.getUuid();
        this.createTime = domain.getCreateTimeAsText();
    }


    /*
    * 判断是否为新建
    * */

    @JsonIgnore
    public boolean isNewly() {
        return StringUtils.isEmpty(uuid) || uuid.equalsIgnoreCase("create");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDto)) return false;

        AbstractDto that = (AbstractDto) o;

        if (!uuid.equals(that.uuid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
