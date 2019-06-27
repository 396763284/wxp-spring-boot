package per.wxp.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wxp.provider.service.TestService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
public class TestController {

    @Reference
    private TestService service;

    @GetMapping(value = "test")
    public Map<String ,Object> test(){
        Map<String ,Object> map=new HashMap<>();
        List<Map<String ,Object>> list=  service.test();
        map.put("aa","bb");
        map.put("list",list);
        return map;
    }

}
