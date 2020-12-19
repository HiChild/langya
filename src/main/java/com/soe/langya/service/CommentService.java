package com.soe.langya.service;

import com.github.pagehelper.PageHelper;
import com.soe.langya.mapper.CommentMapper;
import com.soe.langya.mapper.UserMapper;
import com.soe.langya.pojo.Comment;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired(required = false)
    CommentMapper commentMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * add necessary info into comment
     * and put it into database
     * @param comment a comment you want to save
     * @return 0 for success , -1 for fail
     */
    public Integer addOneComment(Comment comment) {
        //设置必要的信息
        addCommentInfo(comment);
        Integer res = commentMapper.save(comment);
        return res <= 0 ? -1 : 0;
    }

    /**
     * delete a comment from database
     * @param comment the comment you want to delete
     * @return 0 for success, -1 for fail
     */
    public Integer deleteByCommentId(Comment comment) {
        Integer res = commentMapper.deleteByCommentId(comment.getCommentId());
        return res <= 0 ? -1 : 0;
    }

    /**
     * select comments by userId
     * @param userId the User's id you should provide
     * @return A List of comment which belongs to User
     */
    public List<Comment> findByUserId(Integer userId) {
        List<Comment> comments = commentMapper.findByUserId(userId);
        addFaceUrl(comments);
        return comments;
    }

    /**
     *  add necessary information into comment
     *  such as editTime mainContent
     * @param comment the comment which you want to add info
     */
    private void addCommentInfo(Comment comment) {
        Long lEditTime = System.currentTimeMillis();
        String editTime = String.valueOf(lEditTime);
        comment.setEditTime(editTime);
        comment.setStatus(1);
        String content = comment.getCommentContent();
        comment.setMainContent(content.substring(0,Math.min(content.length(),50)));
        addUserNameIntoComment(comment);

    }

    /**
     * select comments by artId
     * @param artId the article's id you should provide
     * @return A List of comment which belongs to article
     */
    public List<Comment> findByArtId(Integer artId){
        List<Comment> comments = commentMapper.findByArtId(artId);
        for (Comment comment : comments) {
            addUserNameIntoComment(comment);
        }
        addFaceUrl(comments);
        return comments;
    }

    /**
     * set Username into Comment
     * @param comment the comment witch you should add UserName in it
     */
    private void addUserNameIntoComment(Comment comment) {
        Integer userId = comment.getUserId();
        User user = userMapper.findById(userId);
        comment.setUserName(user.getUser_name());
    }

    private void addFaceUrl(Comment comment){
        Integer userId = comment.getUserId();
        User user = userMapper.findById(userId);
        comment.setFaceUrl(user.getFaceUrl());
    }
    private void addFaceUrl(List<Comment> comments){
        for (Comment comment : comments) {
            addFaceUrl(comment);
        }
    }
}
