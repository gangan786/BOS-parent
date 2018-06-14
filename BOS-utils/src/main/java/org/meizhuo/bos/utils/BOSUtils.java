package org.meizhuo.bos.utils;

import org.apache.struts2.ServletActionContext;
import org.meizhuo.bos.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.utils
 * @ClassName: ${TYPE_NAME}
 * @Description:BOS项目工具类
 * @Author: Gangan
 * @CreateDate: 2018/6/14 14:01
 * @UpdateUser:
 * @UpdateDate: 2018/6/14 14:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class BOSUtils {

    /**
     * 获取访问用户的session
     * @return
     */
    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }

    /**
     * 获取登录的用户对象
     * @return
     */
    public static User getLoginUser(){
        return (User) getSession().getAttribute("loginUser");
    }
}
