package com.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/23 16:27
 */
public class TimeUtils {

    public static String getTimeNow(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
        Date date = new Date();
        String time = formatter.format(date);
        return time;

    }
}
