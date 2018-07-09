package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.Role;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/7/9 11:06
 * @UpdateUser:
 * @UpdateDate: 2018/7/9 11:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

public interface IRoleService {
    void save(Role model, String functionIds);
}
