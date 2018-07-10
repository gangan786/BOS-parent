package org.meizhuo.bos.dao.impl;

import org.meizhuo.bos.dao.IFunctionDao;
import org.meizhuo.bos.dao.base.BaseDaoImpl;
import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.dao.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/8 15:38
 * @UpdateUser:
 * @UpdateDate: 2018/7/8 15:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
    @Override
    public List<Function> findAll() {
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<Function> findFunctionByUser(User user) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
                + " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
        return  (List<Function>) this.getHibernateTemplate().find(hql, user.getId());
    }

    @Override
    public List<Function> findAllMenu() {
        String hql = "FROM Function f WHERE f.generatemenu = '1' order by f.zindex";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<Function> findMenuByUserId(String id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
                + " r LEFT OUTER JOIN r .users u WHERE u.id = ? AND f.generatemenu = '1' "
                + "ORDER BY f.zindex DESC";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
        return list;

    }
}
