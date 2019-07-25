package per.wxp.model;

import java.util.List;

public class PageResult {

    private List<?> list;

    private Integer total;

    public List<?> getData() {
        return list;
    }

    public void setData(List<?> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return total;
    }

    public void setTotalCount(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageResult [list=" + list + ", total=" + total + "]";
    }

}
