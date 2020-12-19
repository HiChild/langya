package com.soe.langya.pojo;

public class Comment {
    private Integer commentId;
    private String commentContent;
    private Integer artId;
    private Integer userId;
    private String editTime;
    private String mainContent;
    private Integer status;
    private String userName;
    private String faceUrl;
    /**
     * 1  发布
     * 0  审核
     * -1 冻结（不可见）
     */

    public Comment() {
    }

    public Comment(Integer commentId, String commentContent, Integer artId, Integer userId, String editTime, Integer status) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.artId = artId;
        this.userId = userId;
        this.editTime = editTime;
        this.status = status;
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

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", artId=" + artId +
                ", userId=" + userId +
                ", editTime='" + editTime + '\'' +
                ", mainContent='" + mainContent + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                '}';
    }
}
