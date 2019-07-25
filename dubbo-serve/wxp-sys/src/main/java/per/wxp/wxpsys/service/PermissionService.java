package per.wxp.wxpsys.service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-06-10 19:01
 */
public interface PermissionService {

    /**
     * 根据usercode查询角色及菜单权限
     * 初始化数据
     * @param params
     * @return
     */
    List<Map<String, Object>> qryMenusRolesByUcode(Map<String, Object> params);


    /**
     * 根据usercode查询用户所有菜单权限
     * 初始化数据
     * @param params
     * @return
     */
    Map<String, Object>  qryPermissionsByUcode(Map<String, Object> params);


    /**
     * 查询权限列表
     * @param map
     * @return
     */
    List<Map<String, Object>> getPermissionList(Map<String, Object> map);

    /**
     * 添加权限
     * @param map
     * @return
     */
    int insertPermission(Map<String, Object> map);

    /**
     * 更新权限
     * @param map
     * @return
     */
    int updatePermission(Map<String, Object> map);

    /**
     * 冻结权限
     * @param map
     * @return
     */
    int freezePermission(Map<String, Object> map);

}
