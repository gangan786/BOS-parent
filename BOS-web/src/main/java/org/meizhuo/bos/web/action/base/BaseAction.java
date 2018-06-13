package org.meizhuo.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 表现层代码抽取接口
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model;

    public static final String HOME="home";

    public BaseAction() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取T的.class文件
        Class<T> entityClass= (Class<T>) superclass.getActualTypeArguments()[0];
        try {
            //通过反射获取具体实例
            model=entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }
}
