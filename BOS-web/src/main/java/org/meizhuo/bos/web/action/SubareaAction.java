package org.meizhuo.bos.web.action;

import org.meizhuo.bos.entity.Subarea;
import org.meizhuo.bos.service.ISubareaService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/17 14:18
 * @UpdateUser:
 * @UpdateDate: 2018/6/17 14:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Resource
    private ISubareaService subareaService;

    public String add(){
        subareaService.save(model);
        return LIST;
    }


}
