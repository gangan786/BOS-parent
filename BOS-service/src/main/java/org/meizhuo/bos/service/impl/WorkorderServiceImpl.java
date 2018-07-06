package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.dao.IWorkordermanageDao;
import org.meizhuo.bos.entity.Workordermanage;
import org.meizhuo.bos.service.IWorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/6 22:03
 * @UpdateUser:
 * @UpdateDate: 2018/7/6 22:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class WorkorderServiceImpl implements IWorkordermanageService {

    @Autowired
    private IWorkordermanageDao workordermanageDao;

    @Override
    public void save(Workordermanage model) {
        workordermanageDao.save(model);
    }
}
