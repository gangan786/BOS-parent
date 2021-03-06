package org.meizhuo.bos.dao.impl;

import org.meizhuo.bos.dao.ISubareaDao;
import org.meizhuo.bos.dao.base.BaseDaoImpl;
import org.meizhuo.bos.entity.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.dao.impl
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/17 14:24
 * @UpdateUser:
 * @UpdateDate: 2018/6/17 14:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {
    @Override
    public List<Object> findSubareasGroupByProvince() {
        String hql="select r.province ,count (*) from Subarea s left outer join s.region r group by r.province";
        return (List<Object>) this.getHibernateTemplate().find(hql);
    }
}
