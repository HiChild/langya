package com.soe.langya.web;

import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.*;
import com.soe.langya.service.PageService;
import com.soe.langya.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/page")
public class PageController {
    @Autowired
    private PageService pageService;
    @RequestMapping("/findAllAsk")
    public PageInfo<Ask> findAllAsk(@RequestBody ReqBean reqBean) {
        List<Ask> asks = pageService.findAllAsk();
        System.out.println(reqBean.getStart() +"  " +reqBean.getSize());
        return  PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), asks);
    }

    @RequestMapping("/findAskByKey")
    public PageInfo<Ask> findAskByKey(@RequestBody ReqBean reqBean) {
        List<Ask> asks = pageService.findAskByKey(reqBean.getKey());
        return  PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), asks);
    }

    @RequestMapping("/findMyAsk")
    public PageInfo<Ask> findMyAsk(@RequestHeader Integer userId,@RequestBody ReqBean reqBean) {
        List<Ask> asks = pageService.findMyAsk(userId);
        return  PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), asks);
    }

    @RequestMapping("/findAskByTag")
    public PageInfo<Ask> findAskByTag(@RequestBody ReqBean reqBean) {
        List<Ask> asks = pageService.findAskByTag(reqBean.getAskTagName());
        return  PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), asks);
    }



    @RequestMapping("/findMyAns")
    public PageInfo<Ans> findMyAns (@RequestHeader Integer userId,@RequestBody ReqBean reqBean) {
        List<Ans> anss = pageService.findMyAns(userId);
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), anss);
    }

    @RequestMapping("/findMyComment")
    public PageInfo<Comment> findMyComment (@RequestHeader Integer userId,@RequestBody ReqBean reqBean) {
        List<Comment> comments = pageService.findMyComment(userId);
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), comments);
    }

    @RequestMapping("/findAllArt")
    public PageInfo<Article> findAllArt (@RequestBody ReqBean reqBean) {
        List<Article> arts = pageService.findAllArt();
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), arts);
    }

    /***待测**/
    @RequestMapping("/findMyArt")
    public PageInfo<Article> findMyArt (@RequestHeader Integer userId,@RequestBody ReqBean reqBean) {
        List<Article> arts = pageService.findMyArt(userId);
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), arts);
    }

    @RequestMapping("/findArtByTag")
    public PageInfo<Article> findArtByTag (@RequestBody ReqBean reqBean) {
        List<Article> arts = pageService.findArtByTag(reqBean.getTagName());
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), arts);
    }

    @RequestMapping("/findArtByKey")
    public PageInfo<Article> findArtByKey (@RequestBody ReqBean reqBean) {
        List<Article> arts = pageService.findArtByKey(reqBean.getKey());
        return PageUtils.getPageInfo(reqBean.getStart(), reqBean.getSize(), arts);
    }
}
