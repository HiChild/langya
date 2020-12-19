package com.soe.langya.mapper;

import com.soe.langya.pojo.LikeCount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeCountMapper {
    @Insert("insert into `likecount` (likeCounts, artId)  values(#{likeCounts}, #{artId})")
    public Integer save(LikeCount likeCount);

    @Select("select count(*) from likecount where artId = #{artId} ")
    public Integer getCountsByArtId(@Param("artId") Integer artId);

    @Select("select * from likecount where artId = #{artId} ")
    public LikeCount findByArtId(@Param("artId") Integer artId);

    @Update("update likecount set " +
            "likeCounts = #{likeCounts} " +
            "where artId = #{artId} ")
    public Integer updateByArtId(LikeCount likeCount);

    @Select("select * from likecount " +
            "order by likeCounts desc " +
            "limit 0 , 10")
    public List<LikeCount> rank();

    @Select("delete from likecount where artId = #{artId} ")
    public Integer delete (@Param("artId") Integer artId);

}
