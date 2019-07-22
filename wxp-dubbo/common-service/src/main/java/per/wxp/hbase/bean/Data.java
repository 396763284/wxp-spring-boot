package per.wxp.hbase.bean;

public abstract class Data implements Val {

    public String content;

    @Override
    public void setValue(Object value) {

        content=(String)value;

    }

    @Override
    public String getValue() {
        return content;
    }
}
