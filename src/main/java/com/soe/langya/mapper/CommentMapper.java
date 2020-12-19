package com.soe.langya.mapper;

import com.soe.langya.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * put a comment into database
     * @param comment the comment you should provide
     * @return 1 for success , others for fail
     */
    @Insert("insert into comment " +
            "(commentContent,artId,userId,editTime,status,mainContent) " +
            "values (#{commentContent},#{artId},#{userId},#{editTime},#{status},#{mainContent})")
    public Integer save(Comment comment);

    /**
     * select a comment by commentId
     * @param commentId the commentId you should provide
     * @return A Comment object you are looking for
     */
    @Select("select * from comment where commentId = #{commentId} ")
    public Comment findByCommentId(@Param("commentId") Integer commentId);

    /**
     * select many comments by artId
     * @param artId the Article's id you should provide
     * @return A List of Comment which is belong to the article
     */
    @Select("select * from comment where artId = #{artId} " +
            "order by commentId desc ")
    public List<Comment> findByArtId(@Param("artId") Integer artId);

    /**
     * select many comments by userId
     * @param userId the user's Id you should provide
     * @return A List of Comment which is belong to User
     */
    @Select(("select * from comment where userId = #{userId} " +
            "order by commentId desc"))
    public List<Comment> findByUserId(@Param("userId") Integer userId);

    /**
     * delete a comment from database by commentId
     * @param commentId the comment's Id you should provide
     * @return 1 for success ,others for fail
     */
    @Delete("delete from comment where commentId = #{commentId} ")
    public Integer deleteByCommentId(@Param("commentId") Integer commentId);

}

//字段
//    private String commentContent;
//    private Integer artId;
//    private Integer userId;
//    private String editTime;
//    private Integer status;