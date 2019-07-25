package per.wxp.wxpsys.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import per.wxp.model.PageResult;
import per.wxp.wxpsys.mapper.RoleMapper;
import per.wxp.wxpsys.service.RoleService;


import java.util.List;
import java.util.Map;

@Service
@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> findRolesByUid(long id) {
        return roleMapper.findRolesByUid(id);
    }

    @Override
    public PageResult getRolePage(Map<String, Object> map) {
        PageResult page=new PageResult();
        List<Map<String ,Object>> list= roleMapper.getRoleList(map);
        int total= roleMapper.getRoleTotal(map);
        page.setData(list);
        page.setTotalCount(total);
        return page;

    }

    @Override
    public List<Map<String,Object>> getRoleList(Map<String, Object> map) {

        return roleMapper.getRoleList(map);
    }

    @Override
    public int insertRole(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int updateRole(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int freezeRole(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int updateRolePermissions(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int insertRolePermissions(Map<String, Object> map) {
        return 0;
    }
}
