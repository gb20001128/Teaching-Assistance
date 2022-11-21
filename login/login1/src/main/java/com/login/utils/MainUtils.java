package com.login.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 18:30
 */

@Component
public class MainUtils {


    //获取四位随机数
    public  static  Integer  random(){

        int x;
        Random ne=new Random();
        x=ne.nextInt(9999-1000+1)+1000;
        return x;
    }



}
