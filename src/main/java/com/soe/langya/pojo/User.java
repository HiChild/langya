package com.soe.langya.pojo;


import java.util.List;

public class User{
    /**
     * 用户主键
     */
    private Integer user_id;
    /**
     * 用户手机
     */
    private String user_phone;
    /**
     * 用户密码
     */
    private String user_password;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 用户邀请码
     */
    private String user_please;
    /**
     * 邀请人数 默认为0
     * */
    private Integer user_please_num;
    /**
     * //用户的级别（状态）
     * -1为账号不可用
     * 0为普通用户
     * 1为评论员
     * 2为系统管理员
     */
    private Integer user_status;

    private boolean enabled;

    private List<Role> roles;

    private String faceUrl;

    private  String email;

    private Integer freeze;


    //getter and setter


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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

    public Integer getUser_please_num() {
        return user_please_num;
    }

    public void setUser_please_num(Integer user_please_num) {
        this.user_please_num = user_please_num;
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }
    //constructor


    public User() {
    }

    public User(String user_phone, String user_password, String user_name, String user_please, Integer user_please_num, Integer user_status) {
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
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", faceUrl='" + faceUrl + '\'' +
                ", email='" + email + '\'' +
                ", freeze=" + freeze +
                '}';
    }
}
