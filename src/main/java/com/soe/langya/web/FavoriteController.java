package com.soe.langya.web;

import com.soe.langya.pojo.Favorite;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("/modify")
    public RespBean modify (@RequestBody Favorite favorite) {
        Integer res = favoriteService.modify(favorite);
        Integer status = favoriteService.findByUserIdAndArtId(favorite);
        if (res == 0 ) {
            return new RespBean(""+status,"success");
        } else {
            return new RespBean("收藏失败","fail");
        }
    }
}
