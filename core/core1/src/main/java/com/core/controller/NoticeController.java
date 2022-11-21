package com.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.Service.NoticeService;
import com.core.utils.TimeUtils;
import common.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 17:04
 */

@Controller
public class NoticeController {


    @Autowired
    NoticeService noticeService;

    @ResponseBody
    @RequestMapping("/selectAllNotice")
    public List<Notice> selectAllNotice(){

//        QueryWrapper <Notice> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        List<Notice> notices = noticeService.list(queryWrapper);
        List<Notice> notices = noticeService.selectAll();
        return notices;
    }

    @ResponseBody
    @RequestMapping("/sendNotice/{userName}/{notice}")
    public boolean sendNotice(@PathVariable("userName") String userName,
                          @PathVariable("notice") String notice){


        Notice myNotice = new Notice();
        myNotice.setTeacherName(userName);
        myNotice.setContent(notice);
        myNotice.setReplyAll(0);

        String time = TimeUtils.getTimeNow();

        myNotice.setTime(time);

        List<Notice> notices = noticeService.saveNotice(myNotice);
        if (notices.size()>0)
        return true;
        return false;
    }


    @ResponseBody
    @RequestMapping("/receiveNotice/{id}")
    public int receiveNotice(@PathVariable("id") String id){

        List<Notice> notices = noticeService.updateNotice(id);

        if (notices.size()>0)
            return 1;
        return 0;

    }


    @ResponseBody
    @RequestMapping("/deleteNotice/{id}")
    public Boolean deleteNotice(@PathVariable("id") String id){
        boolean b = noticeService.deleteById(id);
        return b;
    }


}
