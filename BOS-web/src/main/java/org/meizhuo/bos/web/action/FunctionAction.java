package org.meizhuo.bos.web.action;

import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.service.IFunctionService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/8 15:32
 * @UpdateUser:
 * @UpdateDate: 2018/7/8 15:32
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

    @Autowired
    private IFunctionService functionService;

    public String listajax(){
        List<Function> list=functionService.findAll();
        writeJsonByGson(list,"parentFunction","roles");
        return NONE;
    }

    public String add(){
        functionService.save(model);
        return LIST;
    }

    public String pageQuery(){
        //由于model Function中也有page这个属性，
        // 与pageBean里面的page属性冲突，struts会优先封装到model中，
        // 所以pagebean里面的page字段始终为默认值
        // 这里需要将page值手动分装到pageBean里面
        pageBean.setCurrentPage(Integer.parseInt(model.getPage()));
        functionService.pageQuery(pageBean);
        writeJsonByGson(pageBean,"parentFunction","roles","children");
        return NONE;
    }

}
