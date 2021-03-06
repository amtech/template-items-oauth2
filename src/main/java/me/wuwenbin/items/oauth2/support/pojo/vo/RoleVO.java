package me.wuwenbin.items.oauth2.support.pojo.vo;

import me.wuwenbin.items.oauth2.entity.IRole;

/**
 * 角色管理页面的对象VO
 * Created by tuchen on 2017/7/16.
 */
public class RoleVO extends IRole {

    private String createName;

    private String updateName;

    private String parentName;

    private String systemModuleName;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getSystemModuleName() {
        return systemModuleName;
    }

    public void setSystemModuleName(String systemModuleName) {
        this.systemModuleName = systemModuleName;
    }
}
