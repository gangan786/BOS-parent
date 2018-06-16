package org.meizhuo.bos.web.action.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 表现层代码抽取接口
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model;

    public static final String HOME = "home";
    public static final String LIST = "list";

    public BaseAction() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取T的.class文件
        Class<T> entityClass = (Class<T>) superclass.getActualTypeArguments()[0];
        try {
            //通过反射获取具体实例
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将对象转换为json数据返回
     * @param jsonObject
     */
    public void writeJson(Object jsonObject){
        String json = new Gson().toJson(jsonObject);
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }
}
