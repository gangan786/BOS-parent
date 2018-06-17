package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.Region;

import java.util.ArrayList;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/17 9:53
 * @UpdateUser:
 * @UpdateDate: 2018/6/17 9:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface IRegionService {
    void saveBatch(ArrayList<Region> regions);
}
