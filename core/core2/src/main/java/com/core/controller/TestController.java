package com.core.controller;

import com.core.Service.NoticeService;
import common.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 15:10
 */

@Controller
public class TestController {

    @Autowired
    NoticeService noticeService;



    //测试加通知
    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        Notice notice = new Notice();
        notice.setContent("哈哈哈哈");
        notice.setReplyAll(13);
        notice.setTeacherName("zhang老师");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
        Date date = new Date();//System.currentTimeMillis()

        String time = formatter.format(date);

        notice.setTime(time);

        boolean save = noticeService.save(notice);
        return save+"";

    }


    @ResponseBody
    @RequestMapping("/test1")
    public List<Notice>  test1(){

        List<Notice> notices = noticeService.selectAll();

        return notices;

    }



}
