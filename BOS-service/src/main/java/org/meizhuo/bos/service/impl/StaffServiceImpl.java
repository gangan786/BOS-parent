package org.meizhuo.bos.service.impl;
import org.apache.commons.lang3.StringUtils;
import org.meizhuo.bos.dao.IStaffDao;
import org.meizhuo.bos.entity.Staff;
import org.meizhuo.bos.service.IStaffService;
import org.meizhuo.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/14 22:38
 * @UpdateUser:
 * @UpdateDate: 2018/6/14 22:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {
        if (StringUtils.isNotBlank(ids)){
            String[] split = ids.split(",");
            for (String id : split) {
                staffDao.executeUpdate("staff.delete",id);
            }
        }

    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }
}
