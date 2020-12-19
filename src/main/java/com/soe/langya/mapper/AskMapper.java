package com.soe.langya.mapper;

import com.soe.langya.pojo.Ask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AskMapper {
    /**
     * select all of asks in database
     * @return a list about Ask
     */
    @Select("select * from ask")
    List<Ask> findAll();

    /**
     * insert an ask into database
     * @param ask the Ask object you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Insert("insert into ask" +
            " (askTitle,userId,askContent,askEditTime,askStatus,askMainContent) " +
            "values (#{askTitle},#{userId},#{askContent},#{askEditTime},#{askStatus},#{askMainContent})")
    public Integer save(Ask ask);

    /**
     * delete an ask by askId
     * @param askId the askId you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Delete("delete from ask where askId = #{askId}")
    public Integer delete (@Param("askId") Integer askId);

    /**
     * update an ask
     * @param ask the Ask object you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Update("update ask set askContext = #{ansContent}, " +
            "userId = #{userId}, " +
            "askTitle = #{askTitle}, " +
            "askStatus = #{askStatus}, " +
            "askEditTime = #{askEditTime}, " +
            "askMainContent = #{askMainContent} " +
            "where askId = #{askId}")
    public Integer update(Ask ask);

    /**
     * select asks by userId
     * @param userId the user's userId you should provide
     * @return A List of Ask object which is belong to user
     */
    @Select("select * from ask where userId = #{userId} " +
            "order by askId desc ")
    public List<Ask> findAllById(@Param("userId") Integer userId);

    /**
     * select one ask by askId
     * @param askId an ask's askId you should provide
     * @return an Ask object which askId is your provide
     */
    @Select("select * from ask where askId = #{askId}")
    public Ask findOneById(@Param("askId") Integer askId);

    /**
     * select asks from database by a key-word
     * @param key the key-word you should provide
     * @return A List of Ask which include key-word
     */
    @Select("select * from ask where askTitle like #{key} " +
            "order by askId desc ")
    public List<Ask> findByKeyFromTitle(@Param("key") String key);


    /**
     * select an ask by askEditTime and userId
     * @param askEditTime askEditTime you should provide
     * @param userId userId you should provide
     * @return An ask object
     */
    @Select("select * from ask where askEditTime = #{askEditTime} and userId = #{userId}")
    public Ask findByDateAndUserId(@Param("askEditTime") String askEditTime, @Param("userId") Integer userId);

}
