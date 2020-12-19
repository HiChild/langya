package com.soe.langya.pojo;

public class PageSize {
    private Integer size;
    private Integer start;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "PageSize{" +
                "size=" + size +
                ", start=" + start +
                '}';
    }
}
