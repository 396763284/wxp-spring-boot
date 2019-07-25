package per.wxp.wxpapi.web.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wxp.model.ResultMsg;

import per.wxp.utils.ResultUtil;
import per.wxp.wxpsys.service.PermissionService;
import per.wxp.wxpsys.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-06-21 11:04
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Reference
    private PermissionService permissionService;

    @Reference
    private UserService userService;

    @PostMapping("/getUserInfo")
    public ResultMsg<Map> getUserInfo(@RequestBody Map<String,Object> map){
        logger.info(map.toString());
        Map<String, Object> permsInfo=  permissionService.qryPermissionsByUcode(map);
        Map<String,Object> userInfo= userService.findUserByParams(map);
        logger.info(userInfo.toString());
        Map<String,Object> result= new HashMap<String,Object>();
        result.put("perms_info",permsInfo);
        result.put("user_info",userInfo);
        logger.info(result.toString());
        return ResultUtil.success(result);
    }

}
