package com.soe.langya.service;

import com.soe.langya.mapper.FavoriteMapper;
import com.soe.langya.pojo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired(required = false)
    private FavoriteMapper favoriteMapper;

    public Integer modify(Favorite favorite) {
        Favorite theOne = favoriteMapper.findByUserIdAndArtId(favorite.getUserId(), favorite.getArtId());
        Integer res = 0;
        if (theOne == null) {
            favorite.setReId(0);
            res = favoriteMapper.save(favorite);
        }else {
             res = favoriteMapper.delete(theOne.getFavoriteId());
        }
        return res == 1 ? 0 : -1;
    }

    public Integer findByUserIdAndArtId(Favorite favorite) {
        Favorite res = favoriteMapper.findByUserIdAndArtId(favorite.getUserId(), favorite.getArtId());
        if (res == null) {
            return 0;
        } else {
            return 1;
        }
    }

}
