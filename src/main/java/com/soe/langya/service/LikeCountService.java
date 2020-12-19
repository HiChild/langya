//package com.soe.langya.service;
//
//import com.soe.langya.mapper.LikeCountMapper;
//import com.soe.langya.pojo.LikeCount;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LikeCountService {
//    @Autowired(required = false)
//    private LikeCountMapper likeCountMapper;
//
//
//    public Integer save (LikeCount likeCount) {
//        Integer res = likeCountMapper.save(likeCount);
//        return res == 1 ? 0 : -1;
//    }
//
//    public LikeCount getOneInfoByArtId(Integer artId) {
//         return likeCountMapper.findByArtId(artId);
//    }
//
//    public Integer getCountByArtId(Integer artId) {
//        LikeCount likeCount = getOneInfoByArtId(artId);
//        return likeCount.getLikeCounts();
//    }
//
//    public Integer update (Integer artId) {
//        LikeCount likeCount = getOneInfoByArtId(artId);
//        if (likeCount != null) {
//            Integer newCounts = likeCount.getLikeCounts() + 1;
//            likeCount.setLikeCounts(newCounts);
//        } else {
//            save()
//        }
//    }
//
//}
