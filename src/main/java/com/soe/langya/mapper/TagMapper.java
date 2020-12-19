package com.soe.langya.mapper;

import com.soe.langya.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    /**
     * put a tag into database
     * @param tag
     * @return 1 for success ,negative or zero for fail
     */
    @Insert("insert into tag" +
            " (tagName,artId) " +
            "values (#{tagName},#{artId})")
    public Integer save(Tag tag);

    /**
     * select tags by tagName
     * @param tagName tagName you should provide
     * @return A List of Tag which tagName is your provide
     */
    @Select("select * from tag " +
            "where tagName = #{tagName}" +
            "order by id desc ")
    public List<Tag> findByName(@Param("tagName") String tagName);

    /**
     * select tags by artId
     * @param artId artId you should provide
     * @return A List of Tag which artId is your provide
     */
    @Select("select * from tag where artId = #{artId} " +
            "order by id desc ")
    public List<Tag> findByArtId(@Param("artId") Integer artId);


    @Delete("delete from tag where id = #{id} ")
    public Integer delete(@Param("id") Integer id);

    @Select("select * from tag ")
    public List<Tag> findAll();
}
