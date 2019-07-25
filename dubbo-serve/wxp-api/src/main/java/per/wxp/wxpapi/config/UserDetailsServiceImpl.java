package per.wxp.wxpapi.config;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import per.wxp.wxpsys.service.RoleService;
import per.wxp.wxpsys.service.UserService;


import java.util.*;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private UserService userService;
    @Reference
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        Map<String,Object> userParam=new HashMap<String,Object>();
        userParam.put("user_code",userCode);
        Map<String,Object> userMap= userService.findUserByParams(userParam);
        if (userMap.get("user_id")==null){
            logger.info("login no find name where userCode = {}",userCode );
            throw new UsernameNotFoundException("login no find name where userCode = " +userCode);
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<String> iterator = roleService.findRolesByUid(Long.valueOf(userMap.get("user_id").toString())).iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next()));
        }
        if(iterator==null){
            logger.info("login no find modules by name = "+userCode);
            throw  new AccessDeniedException("login no find modules by userCode ="+userCode);
        }
          return new User(userCode, userMap.get("password").toString(), collection);
        }
}
