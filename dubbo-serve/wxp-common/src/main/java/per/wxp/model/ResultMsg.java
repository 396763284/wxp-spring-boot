package per.wxp.model;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-07-19 18:06
 */
public class ResultMsg<Map> {

    private int code;

    private String msg;

    private Map data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
