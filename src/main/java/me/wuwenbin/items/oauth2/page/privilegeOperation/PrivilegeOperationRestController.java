package me.wuwenbin.items.oauth2.page.privilegeOperation;

import me.wuwenbin.items.oauth2.entity.IPrivilegeOperation;
import me.wuwenbin.items.oauth2.service.PrivilegeOperationService;
import me.wuwenbin.items.oauth2.support.BaseRestController;
import me.wuwenbin.items.oauth2.support.annotation.AuthResource;
import me.wuwenbin.items.oauth2.support.pojo.bo.PrivilegeOperationBO;
import me.wuwenbin.items.oauth2.support.pojo.vo.PrivilegeOperationVO;
import me.wuwenbin.modules.pagination.model.layui.LayTable;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.templateproject.pojo.page.Page;
import org.templateproject.pojo.response.R;

/**
 * created by Wuwenbin on 2017/8/22 at 13:42
 */
@RestController
@RequestMapping("oauth2/privilegeOperation/api")
public class PrivilegeOperationRestController extends BaseRestController {

    private PrivilegeOperationService privilegeOperationService;

    @Autowired
    public void setPrivilegeOperationService(PrivilegeOperationService privilegeOperationService) {
        this.privilegeOperationService = privilegeOperationService;
    }

    @RequestMapping("list")
    @RequiresPermissions("base:privilegeOperation:list")
    @AuthResource(name = "获取操作级权限页面数据")
    public LayTable<PrivilegeOperationVO> privilegeOperationPage(Page<PrivilegeOperationVO> page, PrivilegeOperationBO privilegeOperationBO) {
        page = privilegeOperationService.findPrivilegeOperationPage(page, privilegeOperationBO);
        return layTable(page);
    }

    @RequestMapping("add")
    @RequiresPermissions("base:privilegeOperation:add")
    @AuthResource(name = "添加操作级权限操作")
    public R add(IPrivilegeOperation privilegeOperation) {
        return ajaxDoneAdd("操作级权限", privilegeOperationService, privilegeOperation, IPrivilegeOperation.class);
    }

    @RequestMapping("edit")
    @RequiresPermissions("base:privilegeOperation:edit")
    @AuthResource(name = "编辑操作级权限操作")
    public R edit(String id, String operationName) {
        try {
            if (privilegeOperationService.editPrivilegeOperationName(id, operationName)) {
                return R.ok("修改操作权限名称成功！");
            } else {
                return R.error("修改操作权限名称失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改{}异常，异常原因：{}", e);
            return R.error("修改操作权限名称出现异常，异常信息：" + e.getMessage());
        }
    }

    @RequestMapping("delete")
    @RequiresPermissions("base:privilegeOperation:delete")
    @AuthResource(name = "删除操作级权限操作")
    public R delete(String id) {
        return ajaxDoneDelete("操作权限名称", id.split(","), privilegeOperationService, IPrivilegeOperation.class);
    }
}
