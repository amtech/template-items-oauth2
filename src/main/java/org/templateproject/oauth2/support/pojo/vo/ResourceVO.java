package org.templateproject.oauth2.support.pojo.vo;

import org.templateproject.oauth2.entity.OauthResource;

/**
 * Created by Liurongqi on 2017/7/17.
 */
public class ResourceVO extends OauthResource {
    private String createName;

    private String updateName;

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

    public String getSystemModuleName() {
        return systemModuleName;
    }

    public void setSystemModuleName(String systemModuleName) {
        this.systemModuleName = systemModuleName;
    }
}
