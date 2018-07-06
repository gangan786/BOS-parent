package org.meizhuo.bos.web.action;

import org.meizhuo.bos.crm_cxf.Customer;
import org.meizhuo.bos.crm_cxf.ICustomerService;
import org.meizhuo.bos.entity.Noticebill;
import org.meizhuo.bos.service.INoticebillService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description: 业务通知单管理
 * @Author: Gangan
 * @CreateDate: 2018/7/6 16:18
 * @UpdateUser:
 * @UpdateDate: 2018/7/6 16:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Scope("prototype")
@Controller
public class NoticebillAction extends BaseAction<Noticebill> {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private INoticebillService noticebillService;

    public String findCustomerByTelephone() {
        Customer customer = customerService.findCustomerByTelp(model.getTelephone());
        writeJsonByGson(customer,"");
        return NONE;
    }

    /**
     * 保存一个业务通知单，并尝试自动分单
     * @return
     */
    public String add(){
        noticebillService.save(model);
        return "noticebill_add";
    }

}
