package org.meizhuo.bos.web.action;

import org.meizhuo.bos.entity.Role;
import org.meizhuo.bos.service.IRoleService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action.base
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/9 11:02
 * @UpdateUser:
 * @UpdateDate: 2018/7/9 11:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    @Autowired
    private IRoleService roleService;


    private String functionIds;

    public String add() {

        roleService.save(model, functionIds);
        return LIST;
    }

    public String pageQuery() {
        roleService.pageQuery(pageBean);
        writeJsonByGson(pageBean, "functions", "users");
        return NONE;
    }

    public String listajax() {
        List<Role> roleList = roleService.findAll();
        writeJsonByGson(roleList,"functions", "users");
        return NONE;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
}
