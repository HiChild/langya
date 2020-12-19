package com.soe.langya.pojo;

import java.util.Arrays;
import java.util.List;

public class Ask {
    private Integer askId;
    private String askTitle;
    private Integer userId;
    private String askContent;
    private String askEditTime;
    private Integer askStatus;
    private String askMainContent;
    private String[] askTagNames;
    private List<Ans> anss;
    private List<AskTag> askTags;
    private Integer ansLikeCount;
    private Ans lastedAns;
    private String faceUrl;

    public Ask() {
    }

    public Ask(Integer askId, String askTitle, Integer userId, String askContent, String askEditTime, Integer askStatus, String askMainContent) {
        this.askId = askId;
        this.askTitle = askTitle;
        this.userId = userId;
        this.askContent = askContent;
        this.askEditTime = askEditTime;
        this.askStatus = askStatus;
        this.askMainContent = askMainContent;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public Ans getLastedAns() {
        return lastedAns;
    }

    public void setLastedAns(Ans lastedAns) {
        this.lastedAns = lastedAns;
    }

    public List<AskTag> getAskTags() {
        return askTags;
    }

    public void setAskTags(List<AskTag> askTags) {
        this.askTags = askTags;
    }

    public String[] getAskTagNames() {
        return askTagNames;
    }

    public void setAskTagNames(String[] askTagNames) {
        this.askTagNames = askTagNames;
    }

    public List<Ans> getAnss() {
        return anss;
    }

    public void setAnss(List<Ans> anss) {
        this.anss = anss;
    }

    public Integer getAnsLikeCount() {
        return ansLikeCount;
    }

    public void setAnsLikeCount(Integer ansLikeCount) {
        this.ansLikeCount = ansLikeCount;
    }

    public Integer getAskId() {
        return askId;
    }

    public void setAskId(Integer askId) {
        this.askId = askId;
    }

    public String getAskTitle() {
        return askTitle;
    }

    public void setAskTitle(String askTitle) {
        this.askTitle = askTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAskContent() {
        return askContent;
    }

    public void setAskContent(String askContent) {
        this.askContent = askContent;
    }

    public String getAskEditTime() {
        return askEditTime;
    }

    public void setAskEditTime(String askEditTime) {
        this.askEditTime = askEditTime;
    }

    public Integer getAskStatus() {
        return askStatus;
    }

    public void setAskStatus(Integer askStatus) {
        this.askStatus = askStatus;
    }

    public String getAskMainContent() {
        return askMainContent;
    }

    public void setAskMainContent(String askMainContent) {
        this.askMainContent = askMainContent;
    }

    @Override
    public String toString() {
        return "Ask{" +
                "askId=" + askId +
                ", askTitle='" + askTitle + '\'' +
                ", userId=" + userId +
                ", askContent='" + askContent + '\'' +
                ", askEditTime='" + askEditTime + '\'' +
                ", askStatus=" + askStatus +
                ", askMainContent='" + askMainContent + '\'' +
                ", askTagNames=" + Arrays.toString(askTagNames) +
                ", anss=" + anss +
                ", askTags=" + askTags +
                ", ansLikeCount=" + ansLikeCount +
                ", lastedAns=" + lastedAns +
                ", faceUrl='" + faceUrl + '\'' +
                '}';
    }
}
