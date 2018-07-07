package org.meizhuo.bos.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.meizhuo.bos.dao.IUserDao;
import org.meizhuo.bos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

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

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
