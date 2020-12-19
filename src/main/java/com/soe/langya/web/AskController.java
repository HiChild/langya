package com.soe.langya.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.*;
import com.soe.langya.pojo.Ask;
import com.soe.langya.service.AskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*", "null"})
@RestController
@RequestMapping("/ask")
public class AskController {
    @Autowired
    private AskService askService;

    /**
     * put an ask into database
     * @param ask the ask you sould provide from front end
     * @param userId the user's id you should provide from front end
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/save")
    public RespBean save(@RequestBody Ask ask, @RequestHeader Integer userId){
        ask.setUserId(userId);
        Integer res = askService.addOneAsk(ask);
        askService.addAskTags(ask);
        if (res == 0) {
            return new RespBean("提问发布成功", "success");
        } else {
            return new RespBean("提问发布失败", "fail");
        }
    }

//    /**
//     * display all of the asks
//     * @return
//     */
//    @RequestMapping("/show")
//    public RespBean show() {
//        List<Ask> asks = askService.findAll();
//        if (asks != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setAsks(asks);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
    /**
     * 已经被page 的findAllAsk接口替代
     * display all of the asks
     * @return
     */
    @RequestMapping("/show")
    public PageInfo<Ask> show() {
        List<Ask> asks = askService.findAll();
        PageInfo<Ask> page = new PageInfo<>(asks);
        return page;
    }

    /**
     * delete an ask from database
     * @param ask the ask you should provide from front end
     * @return
     */
    @RequestMapping("/delete")
    public RespBean delete(@RequestBody Ask ask) {
        Integer res = askService.deleteOneAsk(ask);
        if (res == 0) {
            return new RespBean("删除成功","success");
        } else {
            return new RespBean("删除失败","error");
        }
    }

    /**
     * update an ask
     * @param ask the ask you should provide from front end
     * @return
     */
    @RequestMapping("/update")
    public RespBean update(@RequestBody Ask ask) {
        Integer res = askService.update(ask);
        if (res == 0) {
            return new RespBean("更新成功","success");
        } else {
            return new RespBean("更新失败","error");
        }
    }


/**
     * find an ask
     * @param ask the ask you should provide from front end
     * @return
     */
    @RequestMapping("/findOne")
    public RespBean findOne(@RequestBody Ask ask) {
        Ask res = askService.findOneById(ask);
        if (res != null) {
            return new RespBean("查询成功","success",res);
        } else {
            return new RespBean("查询失败","error");
        }
    }

//    /**
//     * find ask which include key-word
//     * @param key the key-word
//     * @return
//     */
//    @RequestMapping("/findByKey")
//    public RespBean findByKey(@RequestParam String key) {
//        List<Ask> asks = askService.findByKey(key);
//        if (asks != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setAsks(asks);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
 /**
  *
  * 已经被 /page/findAskByKey 取代
     * find ask which include key-word
     * @param
     * @return
     */
    @RequestMapping("/findByKey")
    public PageInfo<Ask>  findByKey(@RequestBody ReqBean reqBean) {
        List<Ask> asks = askService.findByKey(reqBean.getKey());
        System.out.println(asks);
        PageInfo<Ask> page = new PageInfo<>(asks);
        return page;
    }

//    /**
//     *
//     * @param userId
//     * @return
//     */
//    @RequestMapping("/file")
//    public RespBean file( @RequestHeader Integer userId) {
//        List<Ask> asks = askService.findAllById(userId);
//        if (asks != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setAsks(asks);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
    /**
     *已经被 /page/findMyAsk 取代
     * @param userId
     * @return
     */
    @RequestMapping("/file")
    public PageInfo<Ask> file(@RequestHeader Integer userId) {
        List<Ask> asks = askService.findAllById(userId);
        PageInfo<Ask> page = new PageInfo<>(asks);
        return page;
    }

//    /**
//     *
//     * @param askTagName
//     * @return
//     */
//    @RequestMapping("/findByTagName")
//    public RespBean findByTagName(@RequestParam String askTagName) {
//        List<Ask> asks = askService.findByAskTag(askTagName);
//        if (asks != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setAsks(asks);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
    /**
     *此方法已经被/page/findAskByTag取代
     * @param reqBean
     * @return
     */
    @RequestMapping("/findByTagName")
    public PageInfo<Ask> findByTagName(@RequestBody ReqBean reqBean) {
        List<Ask> asks = askService.findByAskTag(reqBean.getTagName());
        PageInfo<Ask> page = new PageInfo<>(asks);
        return page;
    }
}
