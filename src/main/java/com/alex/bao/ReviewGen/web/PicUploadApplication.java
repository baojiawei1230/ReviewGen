package com.alex.bao.ReviewGen.web;

import com.alex.bao.ReviewGen.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传Application
 *
 * @Author Alex_Bao
 * @create 2018-08-18 create by IntelliJ IDEA
 */
@SpringBootApplication
@RequestMapping("/pic/upload")
@Controller
public class PicUploadApplication {

    @Value("${host_image}")
    private String HOST_IMAGE;

    @Value("${prefix_pic_path}")
    private String PREFIX_PIC_PATH;

    public static void main(String[] args) {
        SpringApplication.run(PicUploadApplication.class, args);
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 图片上传
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping("/result")
    @ResponseBody
    public String upload(@RequestParam(value = "multipartFile") MultipartFile multipartFile , Model model){
        if(multipartFile != null){
            String originName = multipartFile.getOriginalFilename();
            //简单的校验
            if(!FileUploadUtil.isAllow(FileUploadUtil.getSuffix(originName))){
                return "上传的图片类型不符合,请重试!";
            }
            System.out.println("文件类型校验完毕....");
            //新文件名称
            String newName = FileUploadUtil.picReame(originName);
            File file = new File(PREFIX_PIC_PATH);
            if(!file.exists()){
                file.mkdirs();
            }
            System.out.println("文件夹创建完毕....");
            file = new File(PREFIX_PIC_PATH + newName);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传文件异常....");
            }
            System.out.println("文件上传完毕....");
            String result = HOST_IMAGE + newName;

            return "{\"result\":\""+result+"\"}";
        }
        return "\"{\"result\":\"Null\"}";
    }


}
