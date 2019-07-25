package per.wxp.wxpsys.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface RoleMapper {

     List<String> findRolesByUid(long id);

     List<Map<String ,Object>> findRolesByUcode(Map<String, Object> params);

    /**
     * 角色列表
     * @param map
     * @return
     */
     List<Map<String,Object>> getRoleList(Map<String, Object> map);


    int getRoleTotal(Map<String, Object> map);
}
