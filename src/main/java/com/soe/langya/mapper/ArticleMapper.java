package com.soe.langya.mapper;

import com.soe.langya.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * select all of articles in database
     * @return a list about Article
     */
    @Select("select art_id as id," +
            "art_context as context," +
            "user_id as userId," +
            "art_title as title," +
            "art_status as status," +
            "art_edit_time as editTime," +
            "art_main as mainContext " +
            "from art")
    List<Article> findAll();

    /**
     * insert an article into database
     * @param article the Article object you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Insert("insert into art" +
            " (art_context,user_id,art_title,art_status,art_edit_time,art_main) " +
            "values (#{context},#{userId},#{title},#{status},#{editTime},#{mainContext})")
    public Integer save(Article article);

    /**
     * delete an article by art_id
     * @param id the art_id you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Delete("delete from art where art_id = #{id}")
    public Integer delete (@Param("id") Integer id);

    /**
     * update an article
     * @param article the Article object you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Update("update art set art_context = #{context}, " +
            "user_id = #{userId}, " +
            "art_title = #{title}, " +
            "art_status = #{status}, " +
            "art_edit_time = #{editTime}, " +
            "art_main = #{mainContext} " +
            "where art_id = #{id}")
    public Integer update(Article article);

    /**
     * select articles by userId
     * @param userId the user's userId you should provide
     * @return A List of Article object which is belong to user
     */
    @Select("select art_id as id," +
            "art_context as context," +
            "user_id as userId," +
            "art_title as title," +
            "art_status as status," +
            "art_edit_time as editTime," +
            "art_main as mainContext " +
            "from art " +
            "where user_id = #{id}")
    public List<Article> findAllById(@Param("id") Integer userId);

    /**
     * select one article by art_id
     * @param artId an article's art_id you should provide
     * @return an Article object which art_id is your provide
     */
    @Select("select art_id as id," +
            "art_context as context," +
            "user_id as userId," +
            "art_title as title," +
            "art_status as status," +
            "art_edit_time as editTime," +
            "art_main as mainContext " +
            "from art "+
            "where art_id = #{id}")
    public Article findOneById(@Param("id") Integer artId);

    /**
     * select articles from database by a key-word
     * @param key the key-word you should provide
     * @return A List of Article which include key-word
     */
    @Select("select art_id as id," +
            "art_context as context," +
            "user_id as userId," +
            "art_title as title," +
            "art_status as status," +
            "art_edit_time as editTime," +
            "art_main as mainContext " +
            "from art " +
            "where art_title like #{key}")
    public List<Article> findByKeyFromTitle(@Param("key") String key);


    /**
     * select an article by edit_time and userId
     * @param time edit_time you should provide
     * @param userId userId you should provide
     * @return An article object
     */
    @Select("select art_id as id," +
            "art_context as context," +
            "user_id as userId," +
            "art_title as title," +
            "art_status as status," +
            "art_edit_time as editTime," +
            "art_main as mainContext " +
            "from art " +
            "where  art_edit_time = #{time} and user_id = #{userId}")
    public Article findByDateAndUserId(@Param("time") String time, @Param("userId") Integer userId);
}
