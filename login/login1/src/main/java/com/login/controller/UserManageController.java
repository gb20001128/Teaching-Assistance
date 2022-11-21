package com.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.login.mapper.TeacherMapper;
import com.login.service.TeacherService;
import common.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/22 9:11
 */

@Controller
public class UserManageController {

    @Autowired
    TeacherMapper teacherMapper;



    //获取当前账户
    @RequestMapping("/userManage")
    public String sendNotice( Model model, HttpSession session){

        String accountType = session.getAttribute("accountType") + "";


        if (accountType.equals("教师账户"))
            model.addAttribute("result",1);
        else
            model.addAttribute("result",0);

        return "userManage";
    }



    //修改密码
    @ResponseBody
    @RequestMapping("/updatePassWord/{newPassWord}/{confirmPassWord}")
    public Boolean updatePassWord(@PathVariable("newPassWord") String newPassWord,
                                 @PathVariable("confirmPassWord") String confirmPassWord,
                                  HttpSession session){

        if (!(newPassWord.equals(confirmPassWord))||newPassWord.length()<6){
            return false;
        }


        String teacherName = session.getAttribute("teacherName") + "";

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_name", teacherName);
        Teacher teacher = new Teacher();
        teacher.setPassWord(newPassWord);
        int result = teacherMapper.update(teacher, queryWrapper);

        return true;
    }


}
