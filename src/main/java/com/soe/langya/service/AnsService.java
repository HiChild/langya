package com.soe.langya.service;

import com.github.pagehelper.PageHelper;
import com.soe.langya.mapper.AnsMapper;
import com.soe.langya.mapper.UserMapper;
import com.soe.langya.pojo.Ans;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnsService {
    @Autowired(required = false)
    AnsMapper ansMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * add necessary info into ans
     * and put it into database
     * @param ans a ans you want to save
     * @return 0 for success , -1 for fail
     */
    public Integer addOneAns(Ans ans) {
        //设置必要的信息
        addAnsInfo(ans);
        Integer res = ansMapper.save(ans);
        return res <= 0 ? -1 : 0;
    }

    /**
     * delete a ans from database
     * @param ans the ans you want to delete
     * @return 0 for success, -1 for fail
     */
    public Integer deleteByAnsId(Ans ans) {
        Integer res = ansMapper.deleteByAnsId(ans.getAnsId());
        return res <= 0 ? -1 : 0;
    }

    /**
     * select anss by userId
     * @param userId the User's id you should provide
     * @return A List of ans which belongs to User
     */
    public List<Ans> findByUserId(Integer userId) {
        List<Ans> anss = ansMapper.findByUserId(userId);
        addUserFace(anss);
        return anss;
    }

    /**
     *  add necessary information into ans
     *  such as editTime mainContent
     * @param ans the ans which you want to add info
     */
    private void addAnsInfo(Ans ans) {
        Long lEditTime = System.currentTimeMillis();
        String editTime = String.valueOf(lEditTime);
        ans.setAnsEditTime(editTime);
        ans.setAnsStatus(1);
        String content = ans.getAnsContent();
        ans.setAnsMainContent(content.substring(0,Math.min(content.length(),50)));
        addUserNameIntoAns(ans);
    }


    /**
     * select anss by askId
     * @param askId the article's id you should provide
     * @return A List of ans which belongs to article
     */
    public List<Ans> findByAskId(Integer askId){
        List<Ans> anss = ansMapper.findByAskId(askId);
        for (Ans ans : anss) {
            addUserNameIntoAns(ans);
            addUserFace(ans);
        }
        return anss;
    }

    /**
     * set Username into Ans
     * @param ans the ans witch you should add UserName in it
     */
    private void addUserNameIntoAns(Ans ans) {
        Integer userId = ans.getUserId();
        User user = userMapper.findById(userId);
        ans.setUserName(user.getUser_name());
    }

    private void addUserFace(Ans ans) {
        Integer userId = ans.getUserId();
        User user = userMapper.findById(userId);
        ans.setFaceUrl(user.getFaceUrl());
    }
    private void addUserFace(List<Ans> anss) {
        for (Ans ans : anss) {
            addUserFace(ans);
        }
    }

}
