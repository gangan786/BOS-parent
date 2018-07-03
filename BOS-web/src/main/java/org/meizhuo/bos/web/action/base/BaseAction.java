package org.meizhuo.bos.web.action.base;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.meizhuo.bos.utils.PageBean;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 表现层代码抽取接口
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model;

    public static final String HOME = "home";
    public static final String LIST = "list";

    protected PageBean pageBean = new PageBean();
    DetachedCriteria detachedCriteria = null;

    public BaseAction() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取T的.class文件
        Class<T> entityClass = (Class<T>) superclass.getActualTypeArguments()[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(this.detachedCriteria);
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
     * 将指定对象转化为json数据格式
     *
     * @param jsonObject
     * @param exclueds   指定忽略字段
     */
    public void writeJson(Object jsonObject, String[] exclueds) {
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        jsonConfig.setExcludes(exclueds);
        String json = JSONObject.fromObject(jsonObject, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定List集合对象转为json，并响应到客户端页面
     *
     * @param o
     * @param exclueds 指定忽略字段
     */
    public void writeJson(List o, String[] exclueds) {
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        jsonConfig.setExcludes(exclueds);
        String json = JSONArray.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Gson将对象序列化为json，
     * 如果字段与指定参数字段一样则过滤不进行序列化
     * @param jsonObject 序列化对象
     * @param models 需要过滤的字段
     */
    public void writeJsonByGson(Object jsonObject, final String... models) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return isContain(fieldAttributes.getName(),models);
            }

            private boolean isContain(String name, String[] models) {
                for (int i = 0; i < models.length; i++) {
                    if (name.equals(models[i])){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        }).create();

        String json = gson.toJson(jsonObject);
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

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }
}
