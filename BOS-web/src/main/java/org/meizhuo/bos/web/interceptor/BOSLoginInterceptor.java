package org.meizhuo.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.utils.BOSUtils;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.interceptor
 * @ClassName: ${TYPE_NAME}
 * @Description: 用于校验用户是否登录
 * @Author: Gangan
 * @CreateDate: 2018/6/14 13:54
 * @UpdateUser:
 * @UpdateDate: 2018/6/14 13:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
//        ActionProxy proxy = invocation.getProxy();
//        String actionName = proxy.getActionName();
//        String namespace = proxy.getNamespace();
//        String url = namespace + actionName;
//        System.out.println(url);
        User user = BOSUtils.getLoginUser();
        if (user == null) {
            return "login";
        } else {
            return invocation.invoke();
        }
    }
}
