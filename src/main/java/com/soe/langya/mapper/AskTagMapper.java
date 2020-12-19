package com.soe.langya.mapper;

import com.soe.langya.pojo.Ask;
import com.soe.langya.pojo.AskTag;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface AskTagMapper {
    /**
     * put a asktag into database
     * @param askTag
     * @return 1 for success ,negative or zero for fail
     */
    @Insert("insert into asktag" +
            " (askTagName,askId) " +
            "values (#{askTagName},#{askId})")
    public Integer save(AskTag askTag);

    /**
     * select asktags by askTagName
     * @param askTagName askTagName you should provide
     * @return A List of AskTag which askTagName is your provide
     */
    @Select("select * from `asktag` where askTagName = #{askTagName} " +
            "order by askTagId desc ")
    public List<AskTag> findByName(@Param("askTagName") String askTagName);

    /**
     * select asktags by askId
     * @param askId askId you should provide
     * @return A List of AskTag which askId is your provide
     */
    @Select("select * from asktag where askId = #{askId} " +
            "order by askTagId desc ")
    public List<AskTag> findByAskId(@Param("askId") Integer askId);

    @Select("select * from `asktag`")
    public List<AskTag> findAll();

    @Delete("delete from `asktag` where askTagId = #{askTagId}")
    Integer delete(@Param("askTagId") Integer askTagId);
}
