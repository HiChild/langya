package com.soe.langya.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.Comment;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*", "null"})
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    /**
     * save a comment into database
     * @param comment the comment
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/save")
    public RespBean save(@RequestBody Comment comment) {
        Integer res = commentService.addOneComment(comment);
        List<Comment> comments = commentService.findByArtId(comment.getArtId());
        if (res == 0) {
            RespBean respBean = new RespBean("评论成功", "success");
            respBean.setComments(comments);
            return respBean;
        } else {
            return new RespBean("评论失败","error");
        }
    }

    /**
     * delete a comment
     * @param comment the comment you want to delete
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/delete")
    public RespBean delete(@RequestBody Comment comment) {
        Integer res = commentService.deleteByCommentId(comment);
        List<Comment> comments = commentService.findByArtId(comment.getArtId());
        if (res == 0) {
            RespBean respBean = new RespBean("评论删除成功", "success");
            respBean.setComments(comments);
            return respBean;
        } else {
            return new RespBean("评论删除失败","error");
        }
    }

//    /**
//     * display all of comments which belong to user
//     * @param userId the user's id
//     * @return a struct of msg for front end to receive
//     */
//    @RequestMapping("/file")
//    public RespBean file(@RequestHeader Integer userId) {
//        List<Comment> comments = commentService.findByUserId(userId);
//        if (comments != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setComments(comments);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
    /**
     * 此接口已经被/page/findMyComment取代
     * display all of comments which belong to user
     * @param userId the user's id
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/file")
    public PageInfo<Comment> file(@RequestHeader Integer userId) {
        List<Comment> comments = commentService.findByUserId(userId);
        PageInfo<Comment> page = new PageInfo<>(comments);
        return page;
    }
    
}
