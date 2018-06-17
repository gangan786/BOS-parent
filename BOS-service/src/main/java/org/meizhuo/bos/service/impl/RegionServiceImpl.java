package org.meizhuo.bos.service.impl;

import org.meizhuo.bos.dao.IRegionDao;
import org.meizhuo.bos.entity.Region;
import org.meizhuo.bos.service.IRegionService;
import org.meizhuo.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.service.impl
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
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDao;

    @Override
    public void saveBatch(ArrayList<Region> regions) {
        for (Region region : regions) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    /**
     * 根据q模糊查询
     * @param q
     * @return
     */
    @Override
    public List<Region> findListByQ(String q) {

        return regionDao.findListByQ(q);
    }
}
