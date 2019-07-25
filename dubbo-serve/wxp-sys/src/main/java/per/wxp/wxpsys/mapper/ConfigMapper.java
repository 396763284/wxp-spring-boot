package per.wxp.wxpsys.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 系统配置
 * @author: 点岩喵
 * @date: 2019-06-25 11:50
 */
@Mapper
public interface ConfigMapper {

    String getValueByKey(String key);

}
