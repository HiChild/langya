package com.soe.langya.pojo;

public class ReqBean {
    private String key;
    private String askTagName;
    private Integer start;
    private Integer size;
    private String tagName;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAskTagName() {
        return askTagName;
    }

    public void setAskTagName(String askTagName) {
        this.askTagName = askTagName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
