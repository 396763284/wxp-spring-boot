package per.wxp.wxpsys.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-06-26 17:54
 */
@Mapper
public interface RptMapper {
    List<Map<String,Object>> getRptHeadList(Map<String, Object> map);
}
