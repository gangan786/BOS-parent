package org.meizhuo.bos.web.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.service.IUserService;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    @Autowired
    private IUserService userService;

    private String checkcode;

    public String login() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String rightCode = (String) session.get("key");
        if (StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(rightCode)) {
            User user = userService.login(model);
            if (user!=null){
                ActionContext.getContext().getSession().put("loginUser",user);
                session.remove("key");
                return HOME;
            }else {
                this.addActionError("用户名或者密码输入错误");
                return LOGIN;
            }
        } else {
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }


    public String logout() throws Exception {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
