package com.login.controller;

import com.login.service.CoreService;
import common.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 16:50
 */

@Controller
public class NoticeController {


    @Autowired
    CoreService coreService;

    @RequestMapping("/teacherNotice")
    public String teacherNotice(Model model){

        List<Notice> notices = coreService.selectAllNotice();
        model.addAttribute("notices",notices);

        return "teacherNotice";
    }



    //发布通知
    @ResponseBody
    @RequestMapping("/sendNotice/{notice}")
    public Boolean sendNotice(@PathVariable("notice") String notice, Model model, HttpSession session){

        String accountType = session.getAttribute("accountType") + "";

        if (accountType.equals("学生账户"))
            return false;

        String userName = session.getAttribute("userName") + "";

        boolean boo = coreService.sendNotice(userName, notice);


        return boo;
    }


    //收到通知
    @RequestMapping("/receiveNotice/{id}")
    public String receiveNotice(@PathVariable("id") String id){

        Integer integer = coreService.receiveNotice(id);


        return "redirect:/teacherNotice";

    }

    //删除通知
    @RequestMapping("/deleteNotice/{id}/{teacherName}")
    public String deleteNotice(@PathVariable("teacherName") String teacherName,
                               @PathVariable("id") String id,
                               HttpSession session,Model model){

        String userName = session.getAttribute("userName") + "";
        if (userName.equals(teacherName)){
            Boolean aBoolean = coreService.deleteNotice(id);
        }

        else
        model.addAttribute("msg","您没有权限!");

        return "forward:/teacherNotice";

    }


}
