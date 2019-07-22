package per.wxp.provider.mapper;

import com.sun.tools.javac.util.List;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface HiveTestMapper {

    List<Map<String,Object>> testQuery();

}
