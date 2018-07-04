package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.dao.IDecidedzoneDao;
import org.meizhuo.bos.dao.ISubareaDao;
import org.meizhuo.bos.entity.Decidedzone;
import org.meizhuo.bos.entity.Subarea;
import org.meizhuo.bos.service.IDecidedzoneService;
import org.meizhuo.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/3 15:48
 * @UpdateUser:
 * @UpdateDate: 2018/7/3 15:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void  save(Decidedzone model, String[] subareaid) {
        decidedzoneDao.save(model);
        for (String id : subareaid) {
            Subarea sub = subareaDao.findById(id);
            sub.setDecidedzone(model);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
