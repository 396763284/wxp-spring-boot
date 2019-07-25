package per.wxp.model;

/**
 * @description:
 * @author: 点岩喵
 * @date: 2019-07-19 18:18
 */
public enum ResultEnum {
    ERROR(-1,"失败"),
    SUCCESS(1,"成功");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
