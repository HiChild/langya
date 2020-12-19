package com.soe.langya.pojo;

public class Ans {
    private Integer ansId;
    private String ansContent;
    private String ansMainContent;
    private  String ansEditTime;
    private Integer userId;
    private Integer ansStatus;
    private Integer askId;
    private String userName;
    private String faceUrl;


    public Ans() {
    }

    public Ans(Integer ansId, String ansContent, String ansMainContent, String ansEditTime, Integer userId, Integer ansStatus, Integer askId) {
        this.ansId = ansId;
        this.ansContent = ansContent;
        this.ansMainContent = ansMainContent;
        this.ansEditTime = ansEditTime;
        this.userId = userId;
        this.ansStatus = ansStatus;
        this.askId = askId;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public void setAnsContent(String ansContent) {
        this.ansContent = ansContent;
    }

    public String getAnsMainContent() {
        return ansMainContent;
    }

    public void setAnsMainContent(String ansMainContent) {
        this.ansMainContent = ansMainContent;
    }

    public String getAnsEditTime() {
        return ansEditTime;
    }

    public void setAnsEditTime(String ansEditTime) {
        this.ansEditTime = ansEditTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAnsStatus() {
        return ansStatus;
    }

    public void setAnsStatus(Integer ansStatus) {
        this.ansStatus = ansStatus;
    }

    public Integer getAskId() {
        return askId;
    }

    public void setAskId(Integer askId) {
        this.askId = askId;
    }

    @Override
    public String toString() {
        return "Ans{" +
                "ansId=" + ansId +
                ", ansContent='" + ansContent + '\'' +
                ", ansMainContent='" + ansMainContent + '\'' +
                ", ansEditTime='" + ansEditTime + '\'' +
                ", userId=" + userId +
                ", ansStatus=" + ansStatus +
                ", askId=" + askId +
                '}';
    }
}
