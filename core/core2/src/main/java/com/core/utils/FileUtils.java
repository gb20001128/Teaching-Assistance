package com.core.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileUtils {




    //定义一个目标路径,就是我们要把问题文件上传下载到的位置;
    private  static final String BASE_PATH1=
            "F:\\apache-tomcat-8.0.50\\webapps\\problem\\";

    //课件资源保存到的位置
    private  static final String BASE_PATH2= "F:\\Java\\mydoc\\";

    //private  static final String BASE_PATH="/www/server/tomcat/webapps/upload";



    public static ResponseEntity<byte[]> down(String resourcesName) throws Exception{

        String realPath=BASE_PATH2+resourcesName;

        //创建输入流,导向该文件
        InputStream is = new FileInputStream(realPath);
        //创建与文件一样大小的字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字(固定的)
        headers.add("Content-Disposition", "attachment;filename="+resourcesName);
        //设置响应状态码:成功响应
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        //将带有文件下载的响应实体响应给浏览器
        return responseEntity;

    }






    public static String upload(MultipartFile file){
        //获得上传文件的名称
        String filename = file.getOriginalFilename();

        //创建文件实例对象
        File uploadFile = new File(BASE_PATH2,filename);
        //判断当前文件是否存在
        if (!uploadFile.exists()){
            //如果不存在就创建一个文件夹
            uploadFile.mkdirs();
        }
        //执行文件上传的命令
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            return null;
        }

        return filename;
    }


    //学习社区的问题图片上传
    public static String upload2(MultipartFile file){
        //获得上传文件的名称
        String filename = file.getOriginalFilename();

        //创建文件实例对象
        File uploadFile = new File(BASE_PATH1,filename);
        //判断当前文件是否存在
        if (!uploadFile.exists()){
            //如果不存在就创建一个文件夹
            uploadFile.mkdirs();
        }
        //执行文件上传的命令
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            return null;
        }

        return filename;
    }




    public static void delete1(String resourcesName){

        File file = new File(BASE_PATH2+resourcesName);
        file.delete();
    }


    public static void delete2(String photoName){

        File file = new File(BASE_PATH1+photoName);
        file.delete();
    }

}

