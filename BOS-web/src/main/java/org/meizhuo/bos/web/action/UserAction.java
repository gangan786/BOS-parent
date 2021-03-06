package org.meizhuo.bos.web.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.meizhuo.bos.entity.User;
import org.meizhuo.bos.service.IUserService;
import org.meizhuo.bos.utils.BOSUtils;
import org.meizhuo.bos.utils.MD5Utils;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.meizhuo.bos.crm_cxf.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    @Autowired
    private IUserService userService;

    private String checkcode;


    /**
     * 使用shiro框架进行登录
     *
     * @return
     */
    public String login() {
        if (BOSUtils.getLoginUser() == null) {
            Map<String, Object> session = ActionContext.getContext().getSession();
            String rightCode = (String) session.get("key");
            if (StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(rightCode)) {
                //使用shiro框架提供的方法进行认证操作
                Subject subject = SecurityUtils.getSubject();

                UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));

                try {
                    subject.login(token);
                } catch (AuthenticationException e) {
                    this.addActionError("用户名或者密码输入错误");
                    return LOGIN;
                }
                User user = (User) subject.getPrincipal();
                ActionContext.getContext().getSession().put("loginUser", user);
                return HOME;
            } else {
                this.addActionError("输入的验证码错误");
                return LOGIN;
            }
        } else {
            return HOME;
        }

    }

    /**
     * 此方法已被弃用
     * @return
     */
    public String login_back() {
        if (BOSUtils.getLoginUser() == null) {
            Map<String, Object> session = ActionContext.getContext().getSession();
            String rightCode = (String) session.get("key");
            if (StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(rightCode)) {
                User user = userService.login(model);
                if (user != null) {
                    ActionContext.getContext().getSession().put("loginUser", user);
                    return HOME;
                } else {
                    this.addActionError("用户名或者密码输入错误");
                    return LOGIN;
                }
            } else {
                this.addActionError("输入的验证码错误");
                return LOGIN;
            }
        } else {
            return HOME;
        }

    }


    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

    public String editPassword() {
        User user = BOSUtils.getLoginUser();
        String flag = "1";
        try {
            userService.editPassword(user.getId(), model.getPassword());
        } catch (Exception e) {
            flag = "0";
            e.printStackTrace();
        }
        try {
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    private String[] roleIds;
    public String add(){
        userService.add(model,roleIds);
        return LIST;
    }

    public String pageQuery(){
        userService.pageQuery(pageBean);
        writeJsonByGson(pageBean,"noticebills","roles");
        return NONE;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}
