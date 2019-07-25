package per.wxp.wxpsys.service;


import per.wxp.model.PageResult;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /**
     * 根据userid查询 角色编码
     * @param id
     * @return
     */
    List<String> findRolesByUid(long id);

    /**
     * 查询角色列表
     * @param map
     * @return
     */
    PageResult getRolePage(Map<String, Object> map);

    /**
     * 查询角色列表
     * @param map
     * @return
     */
    List<Map<String,Object>> getRoleList(Map<String, Object> map);
    /**
     * 添加角色
     * @param map
     * @return
     */
    int insertRole(Map<String, Object> map);

    /**
     * 更新角色
     * @param map
     * @return
     */
    int updateRole(Map<String, Object> map);

    /**
     * 冻结角色
     * @param map
     * @return
     */
    int freezeRole(Map<String, Object> map);

    /**
     * 更新角色权限
     * @param map
     * @return
     */
    int updateRolePermissions(Map<String, Object> map);

    /**
     * 插入角色权限
     * @param map
     * @return
     */
    int insertRolePermissions(Map<String, Object> map);

}
