package org.meizhuo.bos.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.meizhuo.bos.dao.IFunctionDao;
import org.meizhuo.bos.dao.IUserDao;
import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.realm
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/7 21:35
 * @UpdateUser:
 * @UpdateDate: 2018/7/7 21:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IFunctionDao functionDao;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Function> functionList=null;
        //获取当前用户对象
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user.getUsername().equals("admin")){
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
             functionList= functionDao.findByCriteria(detachedCriteria);
        }else {
           functionList= functionDao.findFunctionByUser(user);
        }
        for (Function function : functionList) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) authenticationToken;
        //获取页面输入的用户名
        String username = passwordToken.getUsername();
        //根据用户名查询数据库中的密码
        User user = userDao.findUserByUserName(username);
        if (user == null) {
            //用户输入的用户名不存在
            return null;
        } else {
            //框架负责比对数据库中密码和页面输入的密码是否一致
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }

    }
}
