package org.meizhuo.bos.dao.impl;

import org.meizhuo.bos.dao.IUserDao;
import org.meizhuo.bos.dao.base.BaseDaoImpl;
import org.meizhuo.bos.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

    @Override
    public User findUserByUserNameAndPassword(User model) {
        String hql="FROM User u where u.username = ? and u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, model.getUsername(), model.getPassword());
        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
