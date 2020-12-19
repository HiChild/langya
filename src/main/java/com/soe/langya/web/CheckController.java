package com.soe.langya.web;

import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.*;
import com.soe.langya.service.ArticleService;
import com.soe.langya.service.CheckService;
import com.soe.langya.service.UserService;
import com.soe.langya.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*","null"})
@RequestMapping("/check")
public class CheckController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CheckService checkService;
    @RequestMapping("/freeze")
    public RespBean freeze (@RequestBody Article article) {
        Article theOne = articleService.findOneById(article.getId());
        Integer res = checkService.freeze(theOne);
        if (res == 0) {
            return new RespBean("冻结成功", "success");
        }else {
            return new RespBean("解冻失败","error");
        }
    }

    @RequestMapping("/unFreeze")
    public RespBean unFreeze (@RequestBody Article article) {
        Article theOne = articleService.findOneById(article.getId());
        Integer res = checkService.unFreeze(theOne);
        if (res == 0) {
            return new RespBean("解冻成功", "success");
        }else {
            return new RespBean("解冻失败","error");
        }
    }

    @RequestMapping("/findAllArt")
    public PageInfo<Article> findAllArt(@RequestBody ReqBean reqBean){
        List<Article> articles = checkService.findAllArt();
        return PageUtils.getPageInfo(reqBean.getStart(),reqBean.getSize(),articles);
    }

    @RequestMapping("/findAllAsk")
    public PageInfo<Ask> findAllAsk(@RequestBody ReqBean reqBean){
        List<Ask> asks = checkService.findAllAsk();
        return PageUtils.getPageInfo(reqBean.getStart(),reqBean.getSize(),asks);
    }

    @RequestMapping("/test")
    public String test (){
        return "test";
    }
}
