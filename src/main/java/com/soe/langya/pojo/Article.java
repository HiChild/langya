package com.soe.langya.pojo;

import java.util.Arrays;
import java.util.List;

public class Article {
    private Integer id;
    private String context;
    private Integer userId;
    private String title;
    private Integer status;
    private String editTime;
    private List<Tag> tags;
    private String[] tagNames;
    private String mainContext;

    public Article() {
    }

    public Article(Integer id, String context, Integer userId, String title, Integer status, String editTime, String mainContext) {
        this.id = id;
        this.context = context;
        this.userId = userId;
        this.title = title;
        this.status = status;
        this.editTime = editTime;
        this.mainContext = mainContext;
    }

    public String[] getTagNames() {
        return tagNames;
    }

    public void setTagNames(String[] tagNames) {
        this.tagNames = tagNames;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getMainContext() {
        return mainContext;
    }

    public void setMainContext(String mainContext) {
        this.mainContext = mainContext;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", editTime='" + editTime + '\'' +
                ", tags=" + tags +
                ", tagNames=" + Arrays.toString(tagNames) +
                ", mainContext='" + mainContext + '\'' +
                '}';
    }

}
