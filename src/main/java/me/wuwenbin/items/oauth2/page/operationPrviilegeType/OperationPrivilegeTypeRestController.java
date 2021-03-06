package me.wuwenbin.items.oauth2.page.operationPrviilegeType;

import me.wuwenbin.items.oauth2.entity.IOptPriType;
import me.wuwenbin.items.oauth2.service.OptPriTypeService;
import me.wuwenbin.items.oauth2.support.BaseRestController;
import me.wuwenbin.items.oauth2.support.annotation.AuthResource;
import me.wuwenbin.items.oauth2.support.pojo.bo.OperationPrivilegeTypeBo;
import me.wuwenbin.items.oauth2.support.pojo.vo.OptPriTypeVO;
import me.wuwenbin.modules.pagination.model.bootstrap.BootstrapTable;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.templateproject.pojo.page.Page;
import org.templateproject.pojo.response.R;

import java.util.List;

/**
 * Created by zhangteng on 2017/7/12.
 */
@RestController
@RequestMapping("oauth2/operationPrivilegeType/api")
public class OperationPrivilegeTypeRestController extends BaseRestController {


    private OptPriTypeService optPriTypeService;

    @Autowired
    public void setOptPriTypeService(OptPriTypeService optPriTypeService) {
        this.optPriTypeService = optPriTypeService;
    }

    /**
     * 权限操作类型page
     *
     * @param page
     * @param operationPrivilegeTypeBo
     * @return
     */
    @RequestMapping("list")
    @RequiresPermissions("base:operationPrivilegeType:list")
    @AuthResource(name = "获取操作级权限列表页面的数据")
    public BootstrapTable<OptPriTypeVO> org(Page<OptPriTypeVO> page, OperationPrivilegeTypeBo operationPrivilegeTypeBo) {
        page = optPriTypeService.findOperationPrivilegeTypePage(page, operationPrivilegeTypeBo);
        return bootstrapTable(page);
    }


    /**
     * 添加操作级权限类型
     *
     * @param resource
     * @return
     */
    @RequestMapping("add")
    @RequiresPermissions("base:operationPrivilegeType:add")
    @AuthResource(name = "添加操作级权限类型操作")
    public R add(IOptPriType resource) {
        return ajaxDoneAdd("操作级权限类型", optPriTypeService, resource, IOptPriType.class);
    }

    /**
     * 编辑操作级权限类型对象
     *
     * @param resource
     * @return
     */
    @RequestMapping("edit")
    @RequiresPermissions("base:operationPrivilegeType:edit")
    @AuthResource(name = "编辑操作级权限类型对象操作")
    public R edit(IOptPriType resource) {
        return ajaxDoneEdit("操作级权限类型", optPriTypeService, resource, IOptPriType.class);
    }


    @RequestMapping("find/operationType/enabled")
    @RequiresPermissions("base:operationPrivilegeType:enabled")
    @AuthResource(name = "查询可用的操作级权限类型的操作")
    public List<IOptPriType> findEnabledOperationTypes() {
        return optPriTypeService.findEnabledListBean(IOptPriType.class);
    }
}
