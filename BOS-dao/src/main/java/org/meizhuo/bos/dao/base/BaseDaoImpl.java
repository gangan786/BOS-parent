package org.meizhuo.bos.dao.base;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.meizhuo.bos.dao.base.IBaseDao;
import org.meizhuo.bos.utils.PageBean;
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
        String hql = "from " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        //为HQL中的问号赋值
        int i=0;
        for (Object object : objects) {
            query.setParameter(i++,object);
        }
        //执行更新
         query.executeUpdate();
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //查询total
        detachedCriteria.setProjection(Projections.rowCount());//指定hibernate框架发出SQL的形式
        List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);//获取total
        pageBean.setTotal(countList.get(0).intValue());
        //查询rows
        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY); //指定hibernate封装对象的方式
        int firstResult=(currentPage-1)*pageSize;
        int maxResults=pageSize;
        List list = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
        pageBean.setRows(list);
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {

        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
