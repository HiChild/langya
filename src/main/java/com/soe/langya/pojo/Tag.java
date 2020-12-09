package com.soe.langya.pojo;

public class Tag {
    Integer id;
    String tagName;
    Integer artId;

    public Tag() {
    }

    public Tag(Integer id, String tagName, Integer artId) {
        this.id = id;
        this.tagName = tagName;
        this.artId = artId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", artId=" + artId +
                '}';
    }
}
