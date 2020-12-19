package com.soe.langya.service;

import com.soe.langya.mapper.ArticleMapper;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.Ask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckService {
    @Autowired(required = false)
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    AskService askService;

    public Integer freeze (Article art) {
        art.setStatus(-1);
        Integer res = articleMapper.updateFreeze(art);
        return res == 1 ? 0 : -1;
    }

    public Integer unFreeze(Article art) {
        art.setStatus(1);
        Integer res = articleMapper.updateFreeze(art);
        return res == 1 ? 0 : -1;
    }

    public List<Article> findAllArt() {
        List<Article> articles = articleService.findAll();
        List<Article> newArticles = new ArrayList<>();
        for (Article article : articles) {
            if (article.getStatus() == 0) {
                newArticles.add(article);
            }
        }
        return newArticles;
    }

    public List<Ask> findAllAsk() {
        List<Ask> asks = askService.findAll();
        List<Ask> newAsk = new ArrayList<>();
        for (Ask ask : asks) {
            if (ask.getAskStatus() == 0) {
                newAsk.add(ask);
            }
        }
        return newAsk;
    }
}
