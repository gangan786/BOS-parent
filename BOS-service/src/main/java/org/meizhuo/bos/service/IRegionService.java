package org.meizhuo.bos.service;

import org.meizhuo.bos.entity.Region;
import org.meizhuo.bos.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

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

    void pageQuery(PageBean pageBean);

    List<Region> findAll();

    List<Region> findListByQ(String q);
}
