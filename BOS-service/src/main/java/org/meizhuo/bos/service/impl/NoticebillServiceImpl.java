package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.crm_cxf.ICustomerService;
import org.meizhuo.bos.dao.IDecidedzoneDao;
import org.meizhuo.bos.dao.INoticebillDao;
import org.meizhuo.bos.dao.IWorkbillDao;
import org.meizhuo.bos.entity.*;
import org.meizhuo.bos.service.INoticebillService;
import org.meizhuo.bos.utils.BOSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/6 16:49
 * @UpdateUser:
 * @UpdateDate: 2018/7/6 16:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

    @Autowired
    private INoticebillDao noticebillDao;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private IWorkbillDao workbillDao;

    /**
     * 保存业务通知单并尝试自动分单
     *
     * @param model
     */
    @Override
    public void save(Noticebill model) {
        User user = BOSUtils.getLoginUser();
        model.setUser(user);
        noticebillDao.save(model);
        String pickaddress = model.getPickaddress();
        String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
        if (decidedzoneId != null) {
            //查询定区id，可以完成自动分单
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();//与定区关联的取派员
            model.setStaff(staff);
            model.setArrivecity(Noticebill.ORDERTYPE_AUTO);
            //为取派员产生一个工单
            Workbill workbill = new Workbill();
            workbill.setType(Workbill.TYPE_1);
            workbill.setStaff(staff);
            workbill.setRemark(model.getRemark());
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setNoticebill(model);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setAttachbilltimes(0);

            workbillDao.save(workbill);//保存工单

            //调用短信平台发送短信

        } else {
            model.setArrivecity(Noticebill.ORDERTYPE_MAN);
        }
    }
}
