import org.junit.Test;
import org.junit.runner.RunWith;
import org.meizhuo.bos.entity.Staff;
import org.meizhuo.bos.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ProjectName: BOS-parent
 * @Package: PACKAGE_NAME
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/6/16 10:06
 * @UpdateUser:
 * @UpdateDate: 2018/6/16 10:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AddStaff {

    @Autowired
    private IStaffService staffService;
    @Test
    public void addTestData(){
        for (int i = 0; i < 30; i++) {
            Staff staff = new Staff();
            staff.setName("虚伪儿子1"+i);
            staff.setTelephone((1590987786+i)+"");
            staff.setHaspda("1");
            staff.setStation("江门本地最高学府");
            staff.setStandard("20公斤");
            staffService.save(staff);
        }
    }

}
