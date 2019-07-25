package per.wxp.wxpapi.web;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import per.wxp.model.ResultMsg;
import per.wxp.utils.ResultUtil;
import per.wxp.wxpsys.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-07-24 10:07
 */
@RestController
@RequestMapping(value = "/info")
public class TestController {

    @Reference
    private UserService userService;

    @GetMapping("/test")
    public ResultMsg<Map> getUserInfo(){

        Map<String,Object> param= new HashMap<String,Object>();
        param.put("user_code","admin");

        Map<String,Object> userInfo= userService.findUserByParams(param);

        Map<String,Object> result= new HashMap<String,Object>();

        result.put("user_info",userInfo);
        return ResultUtil.success(result);
    }

    @GetMapping("/test2")
    public String test(){


        return "aaa";
    }

}
