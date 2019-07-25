package per.wxp.wxpsys.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    Map<String,Object> findUserByParams(Map<String, Object> map);

    List< Map<String,Object>> getUserList(Map<String, Object> map);

    int getuserTotal(Map<String, Object> map);

    List<Map<String,Object>> getUserRoleList(Map<String, Object> map);

    int insertUser(Map<String, Object> map);

    /**
     * 插入用户角色
     * @param map
     * @return
     */
    int insertUserRole(Map<String, Object> map);

    /**
     * 删除当前用户的角色
     * @param map
     * @return
     */
    int deleteUserRole(Map<String, Object> map);

}