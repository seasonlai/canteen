import com.season.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by Wellhope on 2018/4/20.
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DataTest extends AbstractTestNGSpringContextTests {


    @Autowired
    DataService dataService;

    @Test
    public void countDataTest(){

        dataService.countData(1);

    }

}



