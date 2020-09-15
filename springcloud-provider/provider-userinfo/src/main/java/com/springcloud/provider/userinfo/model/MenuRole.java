package com.springcloud.provider.userinfo.model;

import java.util.Date;

public class MenuRole {
    private Integer menuRoleId;

    private Boolean isAvailable;

    private String menuRoleIdentify;

    private String menuRoleName;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    public Integer getMenuRoleId() {
        return menuRoleId;
    }

    public void setMenuRoleId(Integer menuRoleId) {
        this.menuRoleId = menuRoleId;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getMenuRoleIdentify() {
        return menuRoleIdentify;
    }

    public void setMenuRoleIdentify(String menuRoleIdentify) {
        this.menuRoleIdentify = menuRoleIdentify;
    }

    public String getMenuRoleName() {
        return menuRoleName;
    }

    public void setMenuRoleName(String menuRoleName) {
        this.menuRoleName = menuRoleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}