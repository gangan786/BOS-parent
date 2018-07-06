package org.meizhuo.bos.web.action;

import org.apache.struts2.ServletActionContext;
import org.meizhuo.bos.entity.Workordermanage;
import org.meizhuo.bos.service.IWorkordermanageService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/6 21:57
 * @UpdateUser:
 * @UpdateDate: 2018/7/6 21:57
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    @Autowired
    private IWorkordermanageService workordermanageService;

    public String add() throws IOException {
        String f="1";
        try {
            workordermanageService.save(model);
        } catch (Exception e) {
            e.printStackTrace();
            f="0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }

}
