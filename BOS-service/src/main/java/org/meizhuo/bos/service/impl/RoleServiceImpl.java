package org.meizhuo.bos.service.impl;

import org.apache.commons.lang.StringUtils;
import org.meizhuo.bos.dao.IRoleDao;
import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.entity.Role;
import org.meizhuo.bos.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/9 11:07
 * @UpdateUser:
 * @UpdateDate: 2018/7/9 11:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public void save(Role model, String functionIds) {
        roleDao.save(model);
        if (StringUtils.isNotBlank(functionIds)){
            String[] split = functionIds.split(",");
            for (String s : split) {
                Function function = new Function();
                function.setId(s);
                model.getFunctions().add(function);
            }
        }
    }
}
