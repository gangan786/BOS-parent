package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.utils.PageBean;

public interface IUserService {
    User login(User model);

    void editPassword(String id, String password);

    void add(User model, String[] roleIds);

    void pageQuery(PageBean pageBean);
}
