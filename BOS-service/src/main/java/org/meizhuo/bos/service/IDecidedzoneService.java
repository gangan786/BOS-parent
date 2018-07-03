package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.Decidedzone;
import org.meizhuo.bos.utils.PageBean;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/3 15:44
 * @UpdateUser:
 * @UpdateDate: 2018/7/3 15:44
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface IDecidedzoneService {
    void save(Decidedzone model,String[] subareaid);
    void pageQuery(PageBean pageBean);
}
