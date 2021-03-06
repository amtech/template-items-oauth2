package me.wuwenbin.items.oauth2.page.role;

import me.wuwenbin.items.oauth2.support.TemplateController;
import me.wuwenbin.items.oauth2.support.annotation.AuthResource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 资源模块控制层
 * 修改备注：增加控制层的注释
 * <p>
 * modify by wuwenbin
 * Created by tuchen on 2017/7/12.
 */
@Controller
@RequestMapping("/oauth2/role")
public class RoleController extends TemplateController {


    @RequestMapping
    @RequiresPermissions("base:role:list")
    @AuthResource(name = "角色列表页面")
    public ModelAndView list() {
        return new ModelAndView("router/role/list");
    }

    @RequestMapping("tree")
    @RequiresPermissions("base:role:tree")
    @AuthResource(name = "角色树页面")
    public ModelAndView Tree() {
        return new ModelAndView("router/role/pIdTree");
    }


}
