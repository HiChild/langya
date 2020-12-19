package com.soe.langya.mapper;

import com.soe.langya.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * to get all of users
     * @return all of users
     */
    @Select("select * from user " +
            "order by user_id ")
    List<User> findAll();

    /**
     * put the user into database
     * @param user :a user you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Insert("insert into user" +
            " (user_phone,user_password,user_name,user_please,user_please_num,user_status,faceUrl,email,freeze) " +
            "values (#{user_phone},#{user_password},#{user_name},#{user_please},#{user_please_num},#{user_status},#{faceUrl},#{email},#{freeze})")
    public Integer save(User user);

    /**
     * delete a user by user_id
     * @param user_id a user_id you should provide
     * @return 1 for success ,negative or zero for fail
     */
    @Delete("delete from user where user_id = #{user_id} ")
    public Integer delete(@Param("user_id") Integer user_id);

    /**
     * update a user's information
     * @param user all information of user
     * @return 1 for success ,negative or zero for fail
     */
    @Update({"update user set user_phone=#{user_phone}," +
            "user_password = #{user_password}, " +
            "user_name = #{user_name}, " +
            "user_please = #{user_please}," +
            "user_please_num = #{user_please_num}, " +
//            "user_status = #{user_status} ," +
//            "faceUrl = #{faceUrl}," +
            "email = #{email} " +
            "where user_id = #{user_id}"})
    public Integer update(User user );

    /**
     * Select a user by user_id
     * @param user_id a user_id you should provide
     * @return A User object what you are looking for
     */
    @Select(("select * from user where user_id = #{user_id} "))
    public User findById(@Param("user_id") Integer user_id);

    /**
     * Select a user by user_name
     * @param user_name a user_name you should provide
     * @return A User object what you are looking for
     */
    @Select(("select * from user where user_name = #{user_name} "))
    public User findByName(@Param("user_name") String user_name);

    /**
     * Select a user according user_name and user_password
     * @param user_name a user_name you should provide
     * @param user_password a user_password you should provide
     * @return A user Object what you are looking for
     */
    @Select("select * from user where user_name = #{user_name} and user_password = #{user_password}")
    public User findByUserNameAndPassword(@Param("user_name") String user_name,@Param("user_password") String user_password);

    /**
     * Select user's status for Roles by user_id
     * @param user_id a user_id you should provide
     * @return A Integer number for different status
     *        * -1为账号不可用      1 for unable
     *          * 0为普通用户       0 for normal user
     *          * 1为评论员         1 for commenter
     *          * 2为系统管理员       2 for system administer
     */
    @Select("select user_status from user where user_id = #{user_id}")
    public Integer findRolesById(@Param("user_id") Integer user_id );

    @Select("select * from user where user_please = #{user_please}")
    public User findUserByPlease(@Param("user_please") String user_please);

    @Update({"update user set " +
            "freeze = #{freeze} " +
            "where user_id = #{user_id}"})
    public Integer updateFreeze(User user);

    @Update({"update user set " +
            "user_status = #{user_status} " +
            "where user_id = #{user_id}"})
    public Integer updateStatus(User user);

    @Update({"update user set " +
            "faceUrl = #{faceUrl} " +
            "where user_id = #{user_id}"})
    public Integer updateUserFaceUrl(User user);

    @Select("select * from user where user_name like #{key} " +
            "order by user_id ")
    public List<User> findByKey(@Param("key") String key);
}
