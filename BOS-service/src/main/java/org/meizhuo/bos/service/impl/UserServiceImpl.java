package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.dao.IUserDao;
import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.service.IUserService;
import org.meizhuo.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public User login(User model) {
        //使用MD5进行加密
       model.setPassword(MD5Utils.md5(model.getPassword()));
        User user=userDao.findUserByUserNameAndPassword(model);
        return user;
    }
}
