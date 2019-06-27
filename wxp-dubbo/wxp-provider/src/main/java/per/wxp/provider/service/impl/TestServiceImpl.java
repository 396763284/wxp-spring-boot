package per.wxp.provider.service.impl;



import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import per.wxp.provider.service.TestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class TestServiceImpl implements TestService {


    @Override
    public List<Map<String, Object>> test() {
        List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
        Map<String, Object> map= new HashMap<>();
        map.put("aa","asdas");
        list.add(map);
        return list;
    }
}
