package per.wxp.wxpsys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.transaction.annotation.Transactional;
import per.wxp.model.PageResult;
import per.wxp.wxpsys.mapper.ConfigMapper;
import per.wxp.wxpsys.mapper.UserMapper;
import per.wxp.wxpsys.service.UserService;


import java.util.List;
import java.util.Map;

@Service
@Component
public class UserServiceImpl implements UserService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConfigMapper configMapper;


    @Override
    public Map<String, Object> findUserByParams(Map<String, Object> map) {
        return userMapper.findUserByParams(map);
    }

    @Override
    public PageResult getUserList(Map<String, Object> map) {
        PageResult page=new PageResult();
        List<Map<String ,Object>> list= userMapper.getUserList(map);
        int total= userMapper.getuserTotal(map);
        page.setData(list);
        page.setTotalCount(total);
        return page;
    }

    @Override
    @Transactional
    public int insertUser(Map<String, Object> map) {
        String initPassword= configMapper.getValueByKey("INIT_PASSWORD");
        String password=crtPassword(initPassword);
        map.put("password",password);
        userMapper.insertUser(map);
        userMapper.insertUserRole(map);
        return 0;
    }

    /**
     * 生成密码
     * @param password
     * @return
     */
    private String crtPassword(String password){
//        String changePsd= "{bcrypt}";

        String changePsd= "{bcrypt}"+new BCryptPasswordEncoder().encode(password);
        return changePsd;
    }

    @Override
    public int updateUser(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int freezeUser(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int updateUserRoles(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int insertUserRoles(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> UserRoleList(Map<String, Object> map) {
        return userMapper.getUserRoleList(map);
    }

    @Override
    @Transactional
    public int updateUserRoleByUserId(Map<String, Object> map) {

        userMapper.deleteUserRole(map);
        userMapper.insertUserRole(map);

        return 0;
    }


}
