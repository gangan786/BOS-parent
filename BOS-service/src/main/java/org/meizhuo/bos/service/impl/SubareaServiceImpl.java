package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.dao.ISubareaDao;
import org.meizhuo.bos.entity.Subarea;
import org.meizhuo.bos.service.ISubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/17 14:22
 * @UpdateUser:
 * @UpdateDate: 2018/6/17 14:22
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void save(Subarea model) {
        subareaDao.save(model);
    }
}
