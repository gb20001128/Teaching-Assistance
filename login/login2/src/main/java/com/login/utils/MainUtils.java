package com.login.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

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
