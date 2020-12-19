package com.soe.langya.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.mapper.AskMapper;
import com.soe.langya.mapper.AnsMapper;
import com.soe.langya.mapper.AskTagMapper;
import com.soe.langya.mapper.UserMapper;
import com.soe.langya.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AskService {
    @Autowired(required = false)
    private AskMapper askMapper;
    @Autowired(required = false)
    private AskTagMapper askTagMapper;
    @Autowired(required = false)
    private AnsMapper ansMapper;
    @Autowired(required = false)
    private AnsService ansService;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private AskService askService;

    private String myDate;

    /**
     * add necessary information into ask and put ask into database
     * @param ask the ask object from front end
     * @return 0 for success,-1 for fail
     */
    public Integer addOneAsk(Ask ask) {
        //添加信息
        addInfo(ask);
        //设置状态
        /**
         * 0表示正常   0 for published
         * 1表示审核中 1 for verify
         * -1表示不可用 -1 for freeze
         */
        ask.setAskStatus(0);
        Integer res = askMapper.save(ask);
        return (res == null || res < 1 )? -1 : 0;
    }

    /**
     * delete an ask from database by ask_id
     * @param ask the ask object from front end
     * @return 0 for success, -1 for fail
     */
    public Integer deleteOneAsk(Ask ask) {
        List<AskTag> askTags = askTagMapper.findByAskId(ask.getAskId());
        for (AskTag askTag : askTags) {
            askTagMapper.delete(askTag.getAskTagId());
        }
        Integer res = askMapper.delete(ask.getAskId());
        return res <= 0 ? -1 : 0;
    }

    /**
     * update an ask from database
     * @param ask the ask object from front end
     * @return 0 for success, -1 for fail
     */
    public Integer update(Ask ask) {
        addInfo(ask);
        Integer res = askMapper.update(ask);
        return res <= 0  ? -1  : 0;
    }

    /**
     * find A asks from database which are belong to user whose user'id is provide
     * @param userId userId you should provide
     * @return A list of Ask which belongs to user whose user'id is provide
     */
    public List<Ask> findAllById(Integer userId) {
        List<Ask> asks = askMapper.findAllById(userId);
        List<Ask> newAsks = new ArrayList<>();
        for (Ask ask : asks) {
            ask = askService.findOneById(ask);
            List<Ans> anss = ansService.findByAskId(ask.getAskId());
            if (anss != null && anss.size()!=0) {
                Ans lastAns = anss.get(0);
                ask.setLastedAns(lastAns);
                ask.setAnss(anss);
            }
            newAsks.add(ask);
        }
        addFaceUrl(newAsks);
        return newAsks;
    }

    /**
     * find asks from database by key-word
     * @param msg the msg is key-word which you should provide
     * @return A List of Ask which includes key-word
     */
    public List<Ask> findByKey(String msg) {
        String key = "%" + msg + "%";
        List<Ask> asks = askMapper.findByKeyFromTitle(key);
        List<Ask> newAsks = new ArrayList<>();
        for (Ask ask : asks) {
            ask = askService.findOneById(ask);
            List<Ans> anss = ansService.findByAskId(ask.getAskId());
            if (anss != null && anss.size()!=0) {
                Ans lastAns = anss.get(0);
                ask.setLastedAns(lastAns);
                ask.setAnss(anss);
            }
            newAsks.add(ask);
        }
        addFaceUrl(newAsks);
        return newAsks;
    }

    /**
     * find an ask from database by askId
     * @param ask ask you should provide from front end
     * @return an ask you want
     */
    public Ask findOneById(Ask ask) {
        Ask theOne = askMapper.findOneById(ask.getAskId());
        if (theOne != null) {
            getAskTags(theOne);
            getAnss(theOne);
            addFaceUrl(theOne);
        }
        return theOne;
    }

    /**
     * find all of asks
     * @return A List of Ask which include all of asks
     */
    public List<Ask> findAll() {
        List<Ask> asks = askMapper.findAll();
        List<Ask> newAsks = new ArrayList<>();
        for (Ask ask : asks) {
            ask = askService.findOneById(ask);
            List<Ans> anss = ansService.findByAskId(ask.getAskId());
            if (anss != null && anss.size()!=0) {
                Ans lastAns = anss.get(0);
                ask.setLastedAns(lastAns);
                ask.setAnss(anss);
            }
            newAsks.add(ask);
        }
        addFaceUrl(newAsks);
        return newAsks;
    }



    /**
     * find asks by askTag
     * @param askTagName askTagName you should provide
     * @return A List of Ask which ask'AskTag is you provide
     */
    public List<Ask> findByAskTag (String askTagName) {
        List<AskTag> askTags = askTagMapper.findByName(askTagName);
        List<Ask> asks = new ArrayList<>();
        for (AskTag t : askTags) {
            Ask oneAsk = askMapper.findOneById(t.getAskId());
            if (oneAsk != null) {
                asks.add(oneAsk);
            }
        }
        List<Ask> newAsks = new ArrayList<>();
        for (Ask ask : asks) {
            ask = askService.findOneById(ask);
            List<Ans> anss = ansService.findByAskId(ask.getAskId());
            if (anss != null && anss.size()!=0) {
                Ans lastAns = anss.get(0);
                ask.setLastedAns(lastAns);
                ask.setAnss(anss);
            }
            newAsks.add(ask);
        }
        addFaceUrl(newAsks);
        return newAsks;
    }

    /**
     * stripHtml for mainContext
     * @param content content you should provide
     * @return A String object which has striped
     */
    private String stripHtml (String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    /**
     * to add AskTags into a ask when ask is going to store into database
     * to add AskTags into database
     * @param ask the ask which you want to add info of askTags
     */
    public void addAskTags (Ask ask) {

        Ask theOne = askMapper.findByDateAndUserId(myDate, ask.getUserId());
        String[] askTagNames = ask.getAskTagNames();
        List<AskTag> askTags = new ArrayList<>();
        for (String askTagName : askTagNames) {
            AskTag askTag = new AskTag();
            askTag.setAskId(theOne.getAskId());
            askTag.setAskTagName(askTagName);
            askTagMapper.save(askTag);
            askTags.add(askTag);
        }
        ask.setAskTags(askTags);
    }

    /**
     * add necessary information into ask
     * @param ask
     */
    private void addInfo(Ask ask) {
        //设置时间和简要内容
        //设置时间戳
        Long date = System.currentTimeMillis();
        myDate = String.valueOf(date);
        ask.setAskEditTime(myDate);
        //设置简要内容
        String stripHtml =stripHtml(ask.getAskContent());
        String mainContext = stripHtml.substring(0, Math.min(stripHtml.length(), 50));
        ask.setAskMainContent(mainContext);
    }

    /**
     * let the ask get their askTags, when an ask is going to be display in front end.
     * so you should take askTags object by ask's Id and put AskTagNames into ask.
     * @param ask
     * @return A List of AskTag which has ask's Id. the the result of return is just for test.
     */
    private List<AskTag> getAskTags(Ask ask) {
        List<AskTag> askTags = askTagMapper.findByAskId(ask.getAskId());
        List<String> askTagNames = new ArrayList<>();
        for (AskTag askTag : askTags) {
            String askTagName = askTag.getAskTagName();
            askTagNames.add(askTagName);
        }
        String[] objects = askTagNames.toArray(new String[askTagNames.size()]);
        ask.setAskTags(askTags);
        ask.setAskTagNames(objects);
        return askTags;
    }

    /**
     * let ask
     * @param ask the ask you should provide
     */
    private void getAnss(Ask ask) {
        List<Ans> anss = ansService.findByAskId(ask.getAskId());
        addUserNameIntoAns(anss);
        ask.setAnss(anss);
    }

    /**
     * set Username into Ans
     * @param anss the anss witch you should add UserName in it
     */
    public void addUserNameIntoAns(List<Ans> anss) {
        for (Ans ans : anss) {
            Integer userId = ans.getUserId();
            User user = userMapper.findById(userId);
            ans.setUserName(user.getUser_name());
        }
    }

    private void addFaceUrl(Ask ask) {
        Integer userId = ask.getUserId();
        User user = userMapper.findById(userId);
        ask.setFaceUrl(user.getFaceUrl());
    }

    private void addFaceUrl(List<Ask> asks) {
        for (Ask ask : asks) {
            addFaceUrl(ask);
        }
    }

}
