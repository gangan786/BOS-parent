package org.meizhuo.bos.dao;

import org.meizhuo.bos.dao.base.IBaseDao;
import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.entity.User;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.dao
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/8 15:38
 * @UpdateUser:
 * @UpdateDate: 2018/7/8 15:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface IFunctionDao extends IBaseDao<Function> {

    List<Function> findFunctionByUser(User user);
}
