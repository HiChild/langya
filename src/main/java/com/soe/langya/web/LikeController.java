package com.soe.langya.web;

import com.soe.langya.pojo.Like;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @RequestMapping("/modify")
    public RespBean modify (@RequestBody Like like) {
        Integer res = likeService.modify(like);
        Integer status = likeService.findByUserIdAndArtId(like);
        if (res == 0 ) {
            return new RespBean(""+status,"success");
        } else {
            return new RespBean("点赞失败","fail");
        }
    }
}
