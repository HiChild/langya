package com.soe.langya.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@CrossOrigin(origins = {"*", "null"})
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    /**
     * put an article into database
     * @param article the article you should provide from front end
     * @param userId the user's id you should provide from front end
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/save")
    public RespBean save(@RequestBody Article article, @RequestHeader Integer userId) {

        article.setUserId(userId);
        Integer res = articleService.addOneArticle(article);
        articleService.addTags(article);
        articleService.insertLikeCountInDatabase(article);
        if (res == 0) {
            return new RespBean("发表成功","success");
        } else {
            return new RespBean("发表失败","error");
        }
    }

    /**
     * delete an article from database
     * @param article the article you should provide from front end
     * @return
     */
    @RequestMapping("/delete")
    public RespBean delete(@RequestBody Article article) {
        Integer res = articleService.deleteOneArticle(article);
        if (res == 0) {
            return new RespBean("删除成功","success");
        } else {
            return new RespBean("删除失败","error");
        }
    }

    /**
     * update an article
     * @param article the article you should provide from front end
     * @return
     */
    @RequestMapping("/update")
    public RespBean update(@RequestBody Article article) {
        Integer res = articleService.update(article);
        if (res == 0) {
            return new RespBean("更新成功","success");
        } else {
            return new RespBean("更新失败","error");
        }
    }

    /**
     * find an article
     * @param article the article you should provide from front end
     * @return
     */
    @RequestMapping("/findOne")
    public RespBean findOne(@RequestBody Article article,@RequestHeader Integer userId) {
        Article res = articleService.findOneById(article , userId);
        if (res != null) {
            return new RespBean("查询成功","success",res);
        } else {
            return new RespBean("查询失败","error");
        }
    }

    /** 此接口已经被/page/findArtByKey取代
     * find article which include key-word
     * @param key the key-word
     * @return
     */
    @RequestMapping("/findByKey")
    public RespBean findByKey(@RequestParam String key,@RequestBody  PageSize pageSize) {
        List<Article> articles = articleService.findByKey(key);
        if (articles != null) {
            RespBean respBean = new RespBean("查询成功", "success");
            respBean.setArticles(articles);
            return respBean;
        } else {
            return new RespBean("查询失败","error");
        }
    }

//    /** 分页前
//     * display all of the articles
//     * @return
//     */
//    @RequestMapping("/show")
//    public RespBean show() {
//        List<Article> articles = articleService.findAll();
//        if (articles != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setArticles(articles);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }

    /** 此接口已经被 /page/findAllArt取代
     * display all of the articles
     * @return
     */
    @RequestMapping("/show")
    public PageInfo<Article> show(@RequestBody  PageSize pageSize) {
        List<Article> articles = articleService.findAll();
        PageInfo<Article> page = new PageInfo<>(articles);
        return page;
    }

//    /**
//     *
//     * @param userId
//     * @return
//     */
//    @RequestMapping("/file")
//    public RespBean file( @RequestHeader Integer userId) {
//        List<Article> articles = articleService.findAllById(userId);
//        if (articles != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setArticles(articles);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
 /**
     *此接口已经被/page/findMyArt取代
     * @param userId
     * @return
     */
    @RequestMapping("/file")
    public PageInfo<Article> file(@RequestHeader Integer userId) {
        List<Article> articles = articleService.findByUserId(userId);
        PageInfo<Article> page = new PageInfo<>(articles);
        return page;
    }

//    /**
//     *
//     * @param tagName
//     * @return
//     */
//    @RequestMapping("/findByTagName")
//    public RespBean findByTagName(@RequestParam String tagName) {
//        List<Article> articles = articleService.findByTag(tagName);
//        if (articles != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setArticles(articles);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
/**
     *此接口已经被/page/findArtByTag取代
     * @param tagName
     * @return
     */
    @RequestMapping("/findByTagName")
    public PageInfo<Article> findByTagName(@RequestParam String tagName) {
        List<Article> articles = articleService.findByTag(tagName);
        PageInfo<Article> page = new PageInfo<>(articles);
        return page;
    }

    @RequestMapping("/rank")
    public List<Article> rank(){
        List<Article> rank = articleService.rank();
        System.out.println(rank.size());
        return rank;
    }
}
