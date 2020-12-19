package com.soe.langya.service;

import com.soe.langya.mapper.LikeCountMapper;
import com.soe.langya.mapper.LikeMapper;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.Like;
import com.soe.langya.pojo.LikeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class LikeService {

    @Autowired(required = false)
    private LikeMapper likeMapper;
    @Autowired(required = false)
    private LikeCountMapper likeCountMapper;

    public Integer modify(Like like) {
        LikeCount theOneLikeCount = likeCountMapper.findByArtId(like.getArtId());
        Like theOne = likeMapper.findByUserIdAndArtId(like.getUserId(), like.getArtId());
        Integer res = 0;
        if (theOne == null) {
            like.setReId(0);
            res = likeMapper.save(like);
            theOneLikeCount.setLikeCounts(theOneLikeCount.getLikeCounts()+1);
        }else {

            res = likeMapper.delete(theOne.getLikeId());
            theOneLikeCount.setLikeCounts(theOneLikeCount.getLikeCounts()-1);
        }
        res = likeCountMapper.updateByArtId(theOneLikeCount);
        return res == 1 ? 0 : -1;
    }

    public void initLikeCount(Article article) {
        LikeCount likeCount = new LikeCount();
        likeCount.setLikeCounts(0);
        likeCount.setArtId(article.getId());
        likeCountMapper.save(likeCount);
    }

    public Integer findByUserIdAndArtId(Like like) {
        Like theLike = likeMapper.findByUserIdAndArtId(like.getUserId(), like.getArtId());
        if (theLike == null) {
            return 0;
        }else {
            return 1;
        }
    }

}
