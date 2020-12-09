package com.soe.langya.web;

import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.pojo.Tag;
import com.soe.langya.pojo.User;
import com.soe.langya.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = {"*", "null"})
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;



    @RequestMapping("/save")
    public RespBean save(@RequestBody Article article, @RequestHeader Integer userId) {

        article.setUserId(userId);
        Integer res = articleService.addOneArticle(article);
        articleService.addTags(article);
        if (res == 0) {
            return new RespBean("发表成功","success");
        } else {
            return new RespBean("发表失败","error");
        }
    }


    @RequestMapping("/delete")
    public RespBean delete(@RequestBody Article article) {
        Integer res = articleService.deleteOneArticle(article);
        if (res == 0) {
            return new RespBean("删除成功","success");
        } else {
            return new RespBean("删除失败","error");
        }
    }
    @RequestMapping("/update")
    public RespBean update(@RequestBody Article article) {
        Integer res = articleService.update(article);
        if (res == 0) {
            return new RespBean("更新成功","success");
        } else {
            return new RespBean("更新失败","error");
        }
    }
    @RequestMapping("/findOne")
    public RespBean findOne(@RequestBody Article article) {
        Article res = articleService.findOneById(article);
        if (res != null) {
            return new RespBean("查询成功","success",res);
        } else {
            return new RespBean("查询失败","error");
        }
    }
    @RequestMapping("/findByKey")
    public RespBean findByKey(@RequestBody String key) {
        List<Article> articles = articleService.findByKey(key);
        if (articles != null) {
            RespBean respBean = new RespBean("查询成功", "success");
            respBean.setArticles(articles);
            return respBean;
        } else {
            return new RespBean("查询失败","error");
        }
    }

    @RequestMapping("/show")
    public RespBean show() {
        List<Article> articles = articleService.findAll();
        if (articles != null) {
            RespBean respBean = new RespBean("查询成功", "success");
            respBean.setArticles(articles);
            return respBean;
        } else {
            return new RespBean("查询失败","error");
        }
    }
    @RequestMapping("/file")
    public RespBean file( @RequestBody Integer userId) {
        List<Article> articles = articleService.findAllById(userId);
        if (articles != null) {
            RespBean respBean = new RespBean("查询成功", "success");
            respBean.setArticles(articles);
            return respBean;
        } else {
            return new RespBean("查询失败","error");
        }
    }

    //未修改
    public RespBean findByTagName(@RequestParam String tagName) {
        List<Article> articles = articleService.findByTag(tagName);
        if (articles != null) {
            RespBean respBean = new RespBean("查询成功", "success");
            respBean.setArticles(articles);
            return respBean;
        } else {
            return new RespBean("查询失败","error");
        }
    }

}
