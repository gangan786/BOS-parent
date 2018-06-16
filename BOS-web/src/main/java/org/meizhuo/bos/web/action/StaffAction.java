package org.meizhuo.bos.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.meizhuo.bos.entity.Staff;
import org.meizhuo.bos.service.IStaffService;
import org.meizhuo.bos.utils.PageBean;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/14 22:33
 * @UpdateUser:
 * @UpdateDate: 2018/6/14 22:33
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;

    public String add(){
        staffService.save(model);
        return LIST;
    }

    private int page;
    private int rows;
    public String pageQuery(){
        PageBean<Staff> pageBean = new PageBean<>();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);//创建离线查询条件
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);//执行完毕后，pageBean里面就有数据了
        this.writeJson(pageBean);
        return NONE;
    }

    private String ids;
    public String deleteBatch(){
        staffService.deleteBatch(ids);
        return LIST;
    }

    public String edit(){
        Staff staff = staffService.findById(model.getId());
        //使用页面提交的数据进行覆盖
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return LIST;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
