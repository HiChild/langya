package com.soe.langya.pojo;

public class Like {
    private Integer likeId;
    private Integer userId;
    private Integer artId;
    private Integer reId;

    public Like() {
    }

    public Like(Integer likeId, Integer userId, Integer artId, Integer reId) {
        this.likeId = likeId;
        this.userId = userId;
        this.artId = artId;
        this.reId = reId;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", userId=" + userId +
                ", artId=" + artId +
                ", reId=" + reId +
                '}';
    }
}
