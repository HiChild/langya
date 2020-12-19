package com.soe.langya.pojo;

public class LikeCount {
    private Integer likeCountId;
    private Integer likeCounts;
    private Integer artId;

    public LikeCount() {
    }

    public LikeCount(Integer likeCountId, Integer likeCounts, Integer artId) {
        this.likeCountId = likeCountId;
        this.likeCounts = likeCounts;
        this.artId = artId;
    }

    public Integer getLikeCountId() {
        return likeCountId;
    }

    public void setLikeCountId(Integer likeCountId) {
        this.likeCountId = likeCountId;
    }

    public Integer getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }
}
