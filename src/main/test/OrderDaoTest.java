import com.google.gson.Gson;
import com.season.dao.OrderDao;
import com.season.dao.Page;
import com.season.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by season on 2018/4/19.
 */

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OrderDaoTest extends AbstractTestNGSpringContextTests{
    @Autowired
    OrderDao orderDao;

    @Test
    public void queryOrderByConditionTest() {

        Page page = orderDao.queryOrderByCondition(1, 10,
                DateUtil.convert2Date("2018-04-20", "yyyy-MM-dd"),
                -1, -1, null);

        System.out.println(new Gson().toJson(page));

    }


}
