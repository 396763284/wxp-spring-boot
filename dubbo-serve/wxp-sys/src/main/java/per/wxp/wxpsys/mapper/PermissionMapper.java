package per.wxp.wxpsys.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-06-10 18:59
 */
@Mapper
public interface PermissionMapper {

    List<Map<String ,Object>> qryPermissionByUser(Map<String, Object> params);

    List<Map<String ,Object>> qryPermsByUser(Map<String, Object> params);

}
