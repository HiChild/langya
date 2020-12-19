package com.soe.langya.mapper;

import com.soe.langya.pojo.Ans;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnsMapper {
    /**
     * put a ans into database
     * @param ans the ans you should provide
     * @return 1 for success , others for fail
     */
    @Insert("insert into ans " +
            "(ansContent,askId,userId,ansEditTime,ansStatus,ansMainContent) " +
            "values (#{ansContent},#{askId},#{userId},#{ansEditTime},#{ansStatus},#{ansMainContent})")
    public Integer save(Ans ans);

    /**
     * select a ans by ansId
     * @param ansId the ansId you should provide
     * @return A Ans object you are looking for
     */
    @Select("select * from ans where ansId = #{ansId} ")
    public Ans findByAnsId(@Param("ansId") Integer ansId);

    /**
     * select many anss by askId
     * @param askId the Article's id you should provide
     * @return A List of Ans which is belong to the ask
     */
    @Select("select * from ans where askId = #{askId} order by ansId desc")
    public List<Ans> findByAskId(@Param("askId") Integer askId);

    /**
     * select many anss by userId
     * @param userId the user's Id you should provide
     * @return A List of Ans which is belong to User
     */
    @Select(("select * from ans where userId = #{userId} order by ansId desc"))
    public List<Ans> findByUserId(@Param("userId") Integer userId);

    /**
     * delete a ans from database by ansId
     * @param ansId the ans's Id you should provide
     * @return 1 for success ,others for fail
     */
    @Delete("delete from ans where ansId = #{ansId} ")
    public Integer deleteByAnsId(@Param("ansId") Integer ansId);
}
