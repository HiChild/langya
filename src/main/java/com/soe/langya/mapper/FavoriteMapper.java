package com.soe.langya.mapper;

import com.soe.langya.pojo.Favorite;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FavoriteMapper {
    @Insert("insert into `favorite` (userId,artId,reId) values(#{userId},#{artId},#{reId})")
    public Integer save (Favorite favorite) ;

    @Delete("delete from `favorite` where favoriteId = #{favoriteId}")
    public Integer delete (@Param("favoriteId") Integer favoriteId);

    @Select("select * from `favorite` where userId = #{userId} and ArtId = #{artId}")
    public Favorite findByUserIdAndArtId(@Param("userId")Integer userId,@Param("artId") Integer artId);
}
