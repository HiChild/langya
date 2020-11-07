package com.soe.langya.mapper;

import com.soe.langya.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    /**
     * 添加用户
     * add
     */
    @Insert("insert into user" +
            " (user_phone,user_password,user_name,user_please,user_please_num,user_status) " +
            "values (#{user_phone},#{user_password},#{user_name},#{user_please},#{user_please_num},#{user_status})")
    public int save(User user);


    @Delete("delete from user where user_id = #{user_id} ")
    public void delete(int user_id);


    @Update("update user set user_phone=#{user_phone}," +
            "user_password = #{user_password}, " +
            "user_name = #{user_name}, " +
            "user_please = #{user_please}," +
            "user_please_num = #{user_please_num}, " +
            "user_status = #{user_status} " +
            "where user_id = #{user_id}")
    public int update(User user );

    @Select(("select * from user where user_id = #{user_id} "))
    public User findById(int user_id);

    /**
     * 根据用户名和密码查询用户
     * 返回值：用户
     */
    @Select("select * from user where user_name = #{user_name} and user_password = #{user_password}")
    public User findByUserNameAndPassword(User user);


}
