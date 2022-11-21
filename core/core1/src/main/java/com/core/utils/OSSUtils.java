package com.core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/1 18:13
 */
public class OSSUtils {


    //返回图片访问路径
    public static String upload(MultipartFile multipartFile){

        //填写端点地址
        String endpoint = "oss-cn-nanjing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tRepvL1XMnE6Mh3A96d";
        String accessKeySecret = "AeK7AGpSEpJwg0ZBgBW25McOjckVQh";
        // 填写Bucket名称
        String bucketName = "gb--bucket";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            //1 获取文件上传的流
            InputStream inputStream = multipartFile.getInputStream();



            //2 获取文件名
            String originname = multipartFile.getOriginalFilename();
            String filename = UUID.randomUUID().toString().replace("-","");
            filename=filename.substring(27);
            String suffix = originname.substring(originname.lastIndexOf("."));
            String newName=filename+suffix;
            String fileUrl ="study_problem" + "/" + newName; //最终图片存储路径

            //3 文件上传到阿里云服务器
            ossClient.putObject(bucketName, fileUrl, inputStream);

            return newName;


        } catch (Exception oe) {
            return "fail";
        }  finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }



    //返回图片访问路径
    public static String deleteObject(String fileName){

        //填写端点地址
        String endpoint = "oss-cn-nanjing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tRepvL1XMnE6Mh3A96d";
        String accessKeySecret = "AeK7AGpSEpJwg0ZBgBW25McOjckVQh";
        // 填写Bucket名称
        String bucketName = "gb--bucket";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {


            //删除
            ossClient.deleteObject(bucketName, "study_problem/"+fileName);
            return "ok";


        } catch (Exception oe) {
            return "fail";
        }  finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }



}
