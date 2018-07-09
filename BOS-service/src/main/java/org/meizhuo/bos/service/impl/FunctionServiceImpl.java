package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.dao.IFunctionDao;
import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.service.IFunctionService;
import org.meizhuo.bos.utils.BOSUtils;
import org.meizhuo.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/8 15:36
 * @UpdateUser:
 * @UpdateDate: 2018/7/8 15:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    @Override
    public void save(Function model) {
        Function parentFunction = model.getParentFunction();
        if (parentFunction != null && parentFunction.getId().equals("")) {
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    @Override
    public List<Function> findMenu() {
        User loginUser = BOSUtils.getLoginUser();
        List<Function> list = null;
        if (loginUser.getUsername().equals("admin")) {
            list = functionDao.findAllMenu();
        } else {
            list = functionDao.findMenuByUserId(loginUser.getId());
        }
        return list;
    }


}
