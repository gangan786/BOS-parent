package org.meizhuo.bos.utils;

import com.google.gson.annotations.Expose;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.utils
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/15 21:08
 * @UpdateUser: 封装分页属性
 * @UpdateDate: 2018/6/15 21:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class PageBean<T> {
    private int currentPage;
    private int pageSize;
    private int total;
    private List<T> rows;
    private DetachedCriteria detachedCriteria;//查询条件

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
