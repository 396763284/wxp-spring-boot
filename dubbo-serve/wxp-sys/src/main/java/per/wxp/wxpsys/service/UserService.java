package per.wxp.wxpsys.service;



import per.wxp.model.PageResult;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 根据id  code 查询用户
     * @param map
     * @return
     */
    Map<String,Object> findUserByParams(Map<String, Object> map);

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    PageResult getUserList(Map<String, Object> map);

    /**
     * 添加用户
     * @param map
     * @return
     */
    int insertUser(Map<String, Object> map);

    /**
     * 更新用户
     * @param map
     * @return
     */
    int updateUser(Map<String, Object> map);

    /**
     * 冻结用户
     * @param map
     * @return
     */
    int freezeUser(Map<String, Object> map);

    /**
     * 更新用户角色
     * @param map
     * @return
     */
    int updateUserRoles(Map<String, Object> map);

    /**
     * 插入用户角色
     * @param map
     * @return
     */
    int insertUserRoles(Map<String, Object> map);


    List<Map<String,Object>> UserRoleList(Map<String, Object> map);


    int updateUserRoleByUserId(Map<String, Object> map);

}
