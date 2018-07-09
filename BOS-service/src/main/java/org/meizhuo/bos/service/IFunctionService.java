package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.Function;
import org.meizhuo.bos.utils.PageBean;

import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/8 15:36
 * @UpdateUser:
 * @UpdateDate: 2018/7/8 15:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface IFunctionService {
    List<Function> findAll();

    void save(Function model);

    void pageQuery(PageBean pageBean);

    List<Function> findMenu();
}
