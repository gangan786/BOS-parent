package org.meizhuo.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;
import org.meizhuo.bos.entity.Region;
import org.meizhuo.bos.service.IRegionService;
import org.meizhuo.bos.utils.PageBean;
import org.meizhuo.bos.utils.PinYin4jUtils;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/17 9:04
 * @UpdateUser:
 * @UpdateDate: 2018/6/17 9:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    @Autowired
    private IRegionService regionService;

    private File regionFile;
    public String importXls() throws IOException {
        ArrayList<Region> regions = new ArrayList<>();
        //使用POI解析
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        //获取第一张标签页
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            Region region = new Region(id, province, city, district, postcode, null, null, null);
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            //城市编码---->>shijiazhuang
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");

            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regions.add(region);
        }

        regionService.saveBatch(regions);

        return NONE;
    }

    public String pageQuery() {

        regionService.pageQuery(pageBean);
        writeJson(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }

    private String q;
    public String listajax(){
        List<Region> regionList=null;
        if (StringUtils.isNoneBlank(q)){
            regionList=regionService.findListByQ(q);
        }else {
            regionList=regionService.findAll();
        }
        this.writeJson(regionList,new String[]{"subareas"});
        return NONE;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
