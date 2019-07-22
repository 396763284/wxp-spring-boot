package Mapper;


import com.sun.tools.javac.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.wxp.provider.mapper.HiveTestMapper;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class hiveTest {

    @Autowired
    private HiveTestMapper mapper;

    @Test
    public  void test(){
        List<Map<String,Object>> list = mapper.testQuery();
        for (Map<String, Object> map : list) {
            System.out.println(map.toString());
        }
    }

}
