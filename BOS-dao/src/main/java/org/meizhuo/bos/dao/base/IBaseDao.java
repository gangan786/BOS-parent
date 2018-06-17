package org.meizhuo.bos.dao.base;

import org.meizhuo.bos.utils.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * 持久层通用接口
 *
 * @param <T>
 */
public interface IBaseDao<T> {
    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void executeUpdate(String queryName, Object... objects);

    void pageQuery(PageBean pageBean);

    void saveOrUpdate(T entity);

}
