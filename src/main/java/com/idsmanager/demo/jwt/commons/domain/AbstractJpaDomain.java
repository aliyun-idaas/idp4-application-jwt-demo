package com.idsmanager.demo.jwt.commons.domain;

/**
 * 2020/9/14
 * <pre>
 *
 * </pre>
 *
 * @author Guilty_Crown
 */

import com.idsmanager.demo.jwt.commons.utils.DateUtils;
import com.idsmanager.demo.jwt.commons.utils.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractJpaDomain implements Serializable {

    private static final long serialVersionUID = 913921567828215144L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected long id;

    @Column(name = "archived")
    protected boolean archived;

    @Version
    @Column(name = "version")
    protected int version;

    @Column(name = "uuid", unique = true)
    protected String uuid = UUIDGenerator.generate();

    @Column(name = "create_time")
    protected Date createTime = DateUtils.now();

    @UpdateTimestamp
    @Column(name = "updated_time")
    @Type(type = "org.hibernate.type.TimestampType")
    protected Date updatedTime;

    public AbstractJpaDomain() {
    }

    public long id() {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractJpaDomain> T id(long id) {
        this.id = id;
        return (T) this;
    }

    public boolean archived() {
        return this.archived;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractJpaDomain> T archived(boolean archived) {
        this.archived = archived;
        return (T) this;
    }

    public String uuid() {
        return this.uuid;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractJpaDomain> T uuid(String uuid) {
        this.uuid = uuid;
        return (T) this;
    }

    public Date createTime() {
        return this.createTime;
    }

    public Date updatedTime() {
        return this.updatedTime;
    }

    public boolean isNewly() {
        return this.id == 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AbstractJpaDomain)) {
            return false;
        } else {
            AbstractJpaDomain that = (AbstractJpaDomain) o;
            return this.uuid.equals(that.uuid);
        }
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    @Override
    public String toString() {
        String sb = "{id=" + this.id +
                ", archived=" + this.archived +
                ", version=" + this.version +
                ", uuid='" + this.uuid + '\'' +
                ", createTime=" + this.createTime +
                ", updatedTime=" + this.updatedTime +
                '}';
        return sb;
    }

    public String getUuid() {
        return uuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreateTimeAsText() {
        return DateUtils.toDateTime(createTime);
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
