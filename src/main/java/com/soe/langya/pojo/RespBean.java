package com.soe.langya.pojo;

import java.util.List;

/**
 * sent message to front end
 */
public class RespBean {
    private String msg;
    private String status;
    private User data;
    private Article article;
    private List<Article> articles;
    private List<User> users;


    public RespBean() {
    }

    public RespBean(String msg, String status, Article article) {
        this.msg = msg;
        this.status = status;
        this.article = article;
    }

    public RespBean(String msg, String status, User data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public RespBean(String msg, String status) {
        this.msg = msg;
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setData(User data) {
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
