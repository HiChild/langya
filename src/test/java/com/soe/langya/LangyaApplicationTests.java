package com.soe.langya;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soe.langya.mapper.ArticleMapper;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.RespBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class LangyaApplicationTests {

    @Test
    void contextLoads() {
        /**
         * 测试获取URL
         */
////            String path = System.getProperty("user.dir")+ "\\src\\main\\resources\\static";
//        String path = null;
//        try {
//            path = ResourceUtils.getFile("classpath:").getPath();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println(path );
        /**
         * 测试PageHelper
         */

    }

}
