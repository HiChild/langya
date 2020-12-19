package com.soe.langya.service;

import com.soe.langya.mapper.ArticleMapper;
import com.soe.langya.mapper.AskMapper;
import com.soe.langya.mapper.AskTagMapper;
import com.soe.langya.mapper.TagMapper;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.Ask;
import com.soe.langya.pojo.AskTag;
import com.soe.langya.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired(required = false)
    TagMapper tagMapper;
    @Autowired(required = false)
    ArticleMapper articleMapper;
    @Autowired(required = false)
    AskTagMapper askTagMapper;
    @Autowired(required = false)
    AskMapper askMapper;

    public Integer cleanTag(){
        Integer sum = 0;
        List<Tag> tags = tagMapper.findAll();
        for (Tag tag : tags) {
            Article article = articleMapper.findOneById(tag.getArtId());
            if (article == null) {
                tagMapper.delete(tag.getId());
                sum++;
            }
        }
        return sum;
    }

    public Integer cleanAskTag(){
        Integer sum = 0;
        List<AskTag> askTags = askTagMapper.findAll();
        for (AskTag tag : askTags) {
            Ask ask = askMapper.findOneById(tag.getAskId());
            if (ask == null) {
                askTagMapper.delete(tag.getAskTagId());
                sum++;
            }
        }
        return sum;
    }

}
