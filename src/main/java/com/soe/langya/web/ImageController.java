package com.soe.langya.web;

import com.soe.langya.pojo.RespBean;
import com.soe.langya.pojo.User;
import com.soe.langya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"*","null"})
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private UserService userService;

    private String path = System.getProperty("user.dir")+File.separator +"src"+File.separator +"main"+File.separator +"resources"+File.separator +"static"+File.separator; //存储默认路径，保存存储路径
    private String realPath = "/default";   //默认为default  为工程的
    private String dFilePath;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(HttpServletRequest req,@RequestHeader Integer userId) throws IOException {
        List<MultipartFile> files = new ArrayList<>();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
        Iterator<String> a = multipartHttpServletRequest.getFileNames(); //返回的数量与前端input数量相同, 返回的字符串即为前端input标签的name
        while (a.hasNext()) {
            String name = a.next();
            List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles(name);//获取单个input标签上传的文件，可能为多个
            files.addAll(multipartFiles);
        }
        upload(files,userId);

        User user = userService.findById(userId);
        if (!user.getFaceUrl().equals("/default.jpg")){
            String originFileName = dFilePath+File.separator+user.getFaceUrl();
            File f = new File(originFileName);
            f.delete();
        }
        user.setFaceUrl(realPath);
        Integer res = userService.updateUserFaceUrl(user);


        return  realPath;
    }

    private void upload(List<MultipartFile> multipartFiles,Integer userId) throws IOException {
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = + System.currentTimeMillis() + userId + multipartFile.getOriginalFilename() ;
            this.realPath = "/" + fileName; //保存访问相对路径
//            String filePath = System.getProperty("user.dir")+File.separator +"src"+File.separator +"main"+File.separator +"resources"+File.separator +"static"+File.separator + "face"; //拼接的方法//保存在开发环境
//            String filePath = ResourceUtils.getFile("classpath:static/face").getPath(); 此路径会保存文件在工程路径下的Target中的static/face/下
            String filePath = System.getProperty("user.dir") + File.separator+"img";  ////工程路径下的img文件
            dFilePath = filePath;
            String fileTotalName = filePath + File.separator + fileName;
            this.path = fileTotalName;    //保存存储相对路径
            File f = new File(fileTotalName);
            multipartFile.transferTo(f);
        }
    }

}
