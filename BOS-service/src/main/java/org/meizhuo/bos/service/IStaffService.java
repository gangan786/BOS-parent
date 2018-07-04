package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.Staff;
import org.meizhuo.bos.utils.PageBean;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/14 22:35
 * @UpdateUser:
 * @UpdateDate: 2018/6/14 22:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface IStaffService {
    void save(Staff staff);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);

    List<Staff> findListNoDelete();
}
