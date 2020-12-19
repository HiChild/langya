package com.soe.langya.pojo;


public class Favorite {
    private Integer favoriteId;
    private Integer userId;
    private Integer artId;
    private Integer reId;

    public Favorite() {
    }

    public Favorite(Integer favoriteId, Integer userId, Integer artId, Integer reId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.artId = artId;
        this.reId = reId;
    }

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
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

    @Override
    public String toString() {
        return "Favorite{" +
                "favoriteId=" + favoriteId +
                ", userId=" + userId +
                ", artId=" + artId +
                '}';
    }
}
