package org.meizhuo.bos.web.action;

import org.meizhuo.bos.entity.Decidedzone;
import org.meizhuo.bos.service.IDecidedzoneService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/3 15:39
 * @UpdateUser:
 * @UpdateDate: 2018/7/3 15:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    //属性驱动，接收多个分区id
    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    @Autowired
    private IDecidedzoneService decidedzoneService;

    /**
     * 添加分区
     *
     * @return
     */
    public String add() {
        decidedzoneService.save(model, subareaid);
        return LIST;
    }

    public String pageQuery() {
        decidedzoneService.pageQuery(pageBean);
        this.writeJsonByGson(pageBean, "currentPage", "detachedCriteria",
                "pageSize", "subareas", "decidedzones");
        return NONE;
    }

}
