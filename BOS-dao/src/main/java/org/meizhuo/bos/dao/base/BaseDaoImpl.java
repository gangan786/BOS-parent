package org.meizhuo.bos.dao.base;

import org.hibernate.SessionFactory;
import org.meizhuo.bos.dao.base.IBaseDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 持久层通用实现
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    private Class<T> entityClass;

    public BaseDaoImpl() {
        ParameterizedType superClass= (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass= (Class<T>) superClass.getActualTypeArguments()[0];
    }

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "form " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }
}
