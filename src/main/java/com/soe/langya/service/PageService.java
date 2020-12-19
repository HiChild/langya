package com.soe.langya.service;

import com.soe.langya.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {
    @Autowired
    private AskService askService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AnsService ansService;

    public List<Ask> findAllAsk(){
        List<Ask> list = askService.findAll();
        List<Ask> asks = new ArrayList<>();
        for (Ask ask : list) {
            if (ask.getAskStatus() == 1){
                asks.add(ask);
            }
        }
        return asks;
    }

    public List<Ask> findAskByKey(String key) {
        List<Ask> list = askService.findByKey(key);
        List<Ask> asks = new ArrayList<>();
        for (Ask ask : list) {
            if (ask.getAskStatus() == 1){
                asks.add(ask);
            }
        }
        return asks;
    }

    public List<Ask> findMyAsk(Integer userId){
        return askService.findAllById(userId);
    }

    public List<Ask> findAskByTag(String tagName) {

        List<Ask> list = askService.findByAskTag(tagName);
        List<Ask> asks = new ArrayList<>();
        for (Ask ask : list) {
            if (ask.getAskStatus() == 1){
                asks.add(ask);
            }
        }
        return asks;
    }

    public List<User> findAllUser(Integer userId){
        List<User> users = userService.findAll();
        List<User> newUser =new ArrayList<>();
        for (User user : users) {
            if (user.getUser_id() == userId) {
                continue;
            }
            newUser.add(user);
        }
        return newUser;
    }

    public List<Ans> findMyAns(Integer userId) {
       return ansService.findByUserId(userId);
    }

    public List<Comment> findMyComment(Integer userId) {
        return commentService.findByUserId(userId);
    }

    public List<Article> findAllArt() {
        List<Article> list = articleService.findAll();
        List<Article> articles = new ArrayList<>();
        for (Article article : list) {
            if (article.getStatus() == 1) {
                articles.add(article);
            }
        }
        return articles;
    }

    public List<Article> findMyArt(Integer userId) {
        return articleService.findByUserId(userId);
    }

    public List<Article> findArtByTag(String tagName){
        List<Article> list = articleService.findByTag(tagName);
        List<Article> articles = new ArrayList<>();
        for (Article article : list) {
            if (article.getStatus() == 1) {
                articles.add(article);
            }
        }
        return articles;
    }

    public List<Article> findArtByKey(String key) {
        List<Article> list = articleService.findByKey(key);
        List<Article> articles = new ArrayList<>();
        for (Article article : list) {
            if (article.getStatus() == 1) {
                articles.add(article);
            }
        }
        return articles;
    }
}
