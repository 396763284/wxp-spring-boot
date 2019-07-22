package per.wxp.hbase.constant;


import per.wxp.hbase.bean.Val;

/**
 * 枚举类
 */
public enum  Names implements Val {
    NAMESPACE("ct")
    ,TABLE("ct:calllog")
    ,CF_CALLER("caller")
    ,CF_CALLEE("callee")
    ,CF_INFO("info")
    ,TOPIC("kafkatest");

    private String name;

    private Names(String name){
        this.name=name;
    }



    @Override
    public void setValue(Object value) {
        this.name =(String) value;
    }

    @Override
    public String getValue() {

        return name;
    }
}
