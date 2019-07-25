package per.wxp.utils;


import per.wxp.model.ResultEnum;
import per.wxp.model.ResultMsg;

import java.util.Map;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-07-19 18:16
 */
public class ResultUtil {
    /**成功**/
    public static ResultMsg success(Map map){
        ResultMsg result = new ResultMsg();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(map);
        return result;
    }

    /**失败**/
    public static ResultMsg error(String msg){
        ResultMsg result = new ResultMsg();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }


}
