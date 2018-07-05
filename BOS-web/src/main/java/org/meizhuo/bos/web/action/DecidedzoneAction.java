package org.meizhuo.bos.web.action;

import org.meizhuo.bos.crm_cxf.Customer;
import org.meizhuo.bos.crm_cxf.ICustomerService;
import org.meizhuo.bos.entity.Decidedzone;
import org.meizhuo.bos.service.IDecidedzoneService;
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

    @Autowired
    private ICustomerService proxy;//在配置文件里面进行了配置但idea似乎不知道，不过跑的时候确实能注入不为null

    public String findListNotAssociation() {
        List<Customer> list = proxy.findListNotAssociation();
        this.writeJsonByGson(list,"");
        return NONE;
    }

    public String findListHasAssociation(){
        List<Customer> list = proxy.findListHasAssociation(model.getId());
        this.writeJsonByGson(list,"");
        return NONE;
    }

    private List<Integer> customerIds;//属性驱动
    public String assigncustomerstodecidedzone(){
        proxy.assigncustomerstodecidedzone(model.getId(),customerIds);
        return LIST;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}
