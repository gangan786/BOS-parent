package org.meizhuo.bos.dao;

import org.meizhuo.bos.dao.base.IBaseDao;
import org.meizhuo.bos.entity.User;

public interface IUserDao extends IBaseDao<User> {

    User findUserByUserNameAndPassword(User model);

    User findUserByUserName(String userName);
}
