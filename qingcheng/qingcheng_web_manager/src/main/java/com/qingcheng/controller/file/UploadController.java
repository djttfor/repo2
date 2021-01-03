package com.qingcheng.controller.file;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    OSS ossClient;

    @PostMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile multipartFile){

        String realPath = request.getSession().getServletContext().getRealPath("img");
        String filename = UUID.randomUUID().toString()+"-"+ multipartFile.getOriginalFilename();

        String filePath = realPath+"/"+filename;
        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            boolean mkdirs = file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://localhost:9101/img/"+filename;
    }

    @RequestMapping("/oss")
    public String ossUpload(@RequestParam("file") MultipartFile multipartFile,String folder){

        String bucketName = "jwdlh";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = folder+"/"+sf.format(new Date())+multipartFile.getOriginalFilename();

        try {
            ossClient.putObject(bucketName,fileName,multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://jwdlh.oss-cn-beijing.aliyuncs.com/"+fileName;
    }
}
