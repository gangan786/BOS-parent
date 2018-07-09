package org.meizhuo.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.meizhuo.bos.dao.IUserDao;
import org.meizhuo.bos.entity.Role;
import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.service.IUserService;
import org.meizhuo.bos.utils.MD5Utils;
import org.meizhuo.bos.utils.PageBean;
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

    /**
     * 根据用户id修改密码
     * @param id
     * @param password
     */
    @Override
    public void editPassword(String id, String password) {
        password=MD5Utils.md5(password);
        userDao.executeUpdate("user.editPassword",password,id);
    }

    @Override
    public void add(User model, String[] roleIds) {
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userDao.save(model);
        if (roleIds!=null&&roleIds.length>0){
            for (String roleId : roleIds) {
                Role role = new Role();
                role.setId(roleId);
                model.getRoles().add(role);
            }
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        userDao.pageQuery(pageBean);
    }
}
