package com.soe.langya.mapper;

import com.soe.langya.pojo.Like;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeMapper {

    @Insert("insert into `like` (userId,artId,reId) values(#{userId},#{artId},#{reId})")
    public Integer save (Like like) ;

    @Delete("delete from `like` where likeId = #{likeId}")
    public Integer delete (@Param("likeId") Integer likeId);

    @Select("select * from `like` where userId = #{userId} and ArtId = #{artId}")
    public Like findByUserIdAndArtId(@Param("userId")Integer userId,@Param("artId") Integer artId);

}
