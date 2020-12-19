package com.soe.langya.pojo;

public class AskTag {
    private Integer askTagId;
    private String askTagName;
    private Integer askId;

    public AskTag() {
    }

    public AskTag(Integer askTagId, String askTagName, Integer askId) {
        this.askTagId = askTagId;
        this.askTagName = askTagName;
        this.askId = askId;
    }

    public Integer getAskTagId() {
        return askTagId;
    }

    public void setAskTagId(Integer askTagId) {
        this.askTagId = askTagId;
    }

    public String getAskTagName() {
        return askTagName;
    }

    public void setAskTagName(String askTagName) {
        this.askTagName = askTagName;
    }

    public Integer getAskId() {
        return askId;
    }

    public void setAskId(Integer askId) {
        this.askId = askId;
    }

    @Override
    public String toString() {
        return "AskTag{" +
                "askTagId=" + askTagId +
                ", askTagName='" + askTagName + '\'' +
                ", askId=" + askId +
                '}';
    }
}
