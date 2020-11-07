package com.soe.langya.pojo;

public class User {
    //用户主键
    int user_id;
    //用户手机
    String user_phone;
    //用户密码
    String user_password;
    //用户名
    String user_name;
    //用户邀请码
    String user_please;
    /**
     * 邀请人数 默认为0
     * */
    int user_please_num;
    /**
     * //用户的级别（状态）
     * -1为账号不可用
     * 0为普通用户
     * 1为评论员
     * 2为系统管理员
     */
    int user_status;

    //getter and setter
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_please() {
        return user_please;
    }

    public void setUser_please(String user_please) {
        this.user_please = user_please;
    }

    public int getUser_please_num() {
        return user_please_num;
    }

    public void setUser_please_num(int user_please_num) {
        this.user_please_num = user_please_num;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }
    //constructor


    public User() {
    }

    public User(String user_phone, String user_password, String user_name, String user_please, int user_please_num, int user_status) {
        this.user_phone = user_phone;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_please = user_please;
        this.user_please_num = user_please_num;
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_phone='" + user_phone + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_please='" + user_please + '\'' +
                ", user_please_num=" + user_please_num +
                ", user_status=" + user_status +
                '}';
    }
}
