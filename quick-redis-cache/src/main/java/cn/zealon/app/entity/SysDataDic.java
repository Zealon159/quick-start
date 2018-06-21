package cn.zealon.app.entity;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * 系统数据字典实体
 * @author zealon
 * @date 2018年2月26日
 *
 */
public class SysDataDic implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String dicName;

    private Integer sortNumber;

    private String dicType;

    private Date created;

    private Date modified;

    private String typeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType == null ? null : dicType.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}