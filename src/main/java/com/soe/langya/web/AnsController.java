package com.soe.langya.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.pojo.Ans;
import com.soe.langya.pojo.PageSize;
import com.soe.langya.pojo.RespBean;
import com.soe.langya.service.AnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*", "null"})
@RestController
@RequestMapping("/ans")
public class AnsController {

    @Autowired
    AnsService ansService;


    /**
     * save a ans into database
     * @param ans the ans
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/save")
    public RespBean save(@RequestBody Ans ans) {
        Integer res = ansService.addOneAns(ans);
        List<Ans> anss = ansService.findByAskId(ans.getAskId());
        if (res == 0) {
            RespBean respBean = new RespBean("评论成功", "success");
            respBean.setAnss(anss);
            return respBean;
        } else {
            return new RespBean("评论失败","error");
        }
    }

    /**
     * delete a ans
     * @param ans the ans you want to delete
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/delete")
    public RespBean delete(@RequestBody Ans ans) {
        Integer res = ansService.deleteByAnsId(ans);
        List<Ans> anss = ansService.findByAskId(ans.getAskId());
        if (res == 0) {
            RespBean respBean = new RespBean("评论删除成功", "success");
            respBean.setAnss(anss);
            return respBean;
        } else {
            return new RespBean("评论删除失败","error");
        }
    }

//    /**
//     * display all of anss which belong to user
//     * @param userId the user's id
//     * @return a struct of msg for front end to receive
//     */
//    @RequestMapping("/file")
//    public RespBean file(@RequestHeader Integer userId) {
//        List<Ans> anss = ansService.findByUserId(userId);
//        if (anss != null) {
//            RespBean respBean = new RespBean("查询成功", "success");
//            respBean.setAnss(anss);
//            return respBean;
//        } else {
//            return new RespBean("查询失败","error");
//        }
//    }
    /**
     * 此接口已经被 /page/findMyAns取代
     * display all of anss which belong to user
     * @param userId the user's id
     * @return a struct of msg for front end to receive
     */
    @RequestMapping("/file")
    public PageInfo<Ans> file(@RequestHeader Integer userId) {
        List<Ans> anss = ansService.findByUserId(userId);
        PageInfo<Ans> page = new PageInfo<>(anss);
        return page;
    }
}
