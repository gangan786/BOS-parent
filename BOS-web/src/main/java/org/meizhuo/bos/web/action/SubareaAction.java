package org.meizhuo.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.meizhuo.bos.entity.Region;
import org.meizhuo.bos.entity.Subarea;
import org.meizhuo.bos.service.ISubareaService;
import org.meizhuo.bos.utils.FileUtils;
import org.meizhuo.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: BOS-parent
 * @Package: org.meizhuo.bos.web.action
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/17 14:18
 * @UpdateUser:
 * @UpdateDate: 2018/6/17 14:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Resource
    private ISubareaService subareaService;

    public String add() {
        subareaService.save(model);
        return LIST;
    }

    public String pageQuery() {
        DetachedCriteria criteria = pageBean.getDetachedCriteria();
        //动态添加过滤条件
        String addresskey = model.getAddresskey();

        if (StringUtils.isNotBlank(addresskey)) {
            criteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }

        Region region = model.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            if (StringUtils.isNotBlank(province)) {
                criteria.createAlias("region", "r");
                criteria.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNoneBlank(city)) {
                criteria.createAlias("region", "r");
                criteria.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                criteria.createAlias("region", "r");
                criteria.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        subareaService.pageQuery(pageBean);
        System.out.println(pageBean);
        this.writeJsonByGson(pageBean, "currentPage", "detachedCriteria", "pageSize",
                "decidedzone", "subareas");

        return NONE;
    }

    /**
     * 导出所有分区数据
     *
     * @return
     */
    public String exportXls() {
        List<Subarea> subareaList = subareaService.findAll();
        //在内存中创建一个Excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建一个标签页
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");
        for (Subarea subarea :
                subareaList) {
            HSSFRow sheetRow = sheet.createRow(sheet.getLastRowNum() + 1);
            sheetRow.createCell(0).setCellValue(subarea.getId());
            sheetRow.createCell(1).setCellValue(subarea.getStartnum());
            sheetRow.createCell(2).setCellValue(subarea.getEndnum());
            sheetRow.createCell(3).setCellValue(subarea.getPosition());
            sheetRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        //输出流进行文件下载
        try {
            String fileName = "分区数据.xls";
            //获取浏览器类型
            String agent = ServletActionContext.getRequest().getHeader("User-Agent");
            fileName = FileUtils.encodeDownloadFilename(fileName, agent);
            String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);//获取文件类型："application/vnd.ms-excel"
            ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
            hssfWorkbook.write(outputStream);
            ServletActionContext.getResponse().setContentType(mimeType);
            ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /**
     * 查询所有未关联到定区的分区
     *
     * @return
     */
    public String listajax() {
        List<Subarea> subareaList = subareaService.findListNotAssociation();
//        this.writeJson(subareaList,new String[]{"decidedzone","region"});
                this.writeJsonByGson(subareaList,"decidedzone","region");
        return NONE;
    }

}
