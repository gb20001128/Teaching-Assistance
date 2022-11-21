package com.login.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.login.mapper.TeacherMapper;
import com.login.myhandler.BlockHandler;
import com.login.service.LoginService;
import com.login.service.TeacherService;
import com.login.utils.MainUtils;
import com.netflix.ribbon.proxy.annotation.Http;
import common.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/18 19:30
 */

@Controller
public class LoginController {



    @Autowired
    LoginService loginService;


    @Autowired
    StringRedisTemplate redisTemplate;


    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    TeacherMapper teacherMapper;


    @Autowired
    TeacherService teacherService;



    @RequestMapping("/")
    public String index(){

        return "door";

    }


    @RequestMapping("/index")
    public String index1(){

        return "index";

    }


    //学生获取验证码
    @ResponseBody
    @RequestMapping("/getCode/{QQ}")
    public Boolean getCode(@PathVariable("QQ") String QQ){

        //获取随机验证码
        String code=MainUtils.random()+"";
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        //设置验证码的过期时间
        operations.set(QQ,code,180, TimeUnit.SECONDS);

        //发送验证码
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("教学辅助系统的通知");
        message.setText("您本次验证码是"+code+" ,当前验证码3分钟内有效");

        //接收方与发送方
        message.setTo(QQ+"@qq.com");
        message.setFrom("2501956263@qq.com");

        mailSender.send(message);

        return true;

    }


    //登录
    @RequestMapping("/logon")
    public String logon(String teacherName,String passWord,
                        String studentName,String QQ,String code, HttpSession session,Model model){



        //教师登录
        if (teacherName!=null&&passWord!=null){

            Teacher teacher = teacherMapper.selectTeacherName(teacherName, passWord);

            if (teacher!=null){
                session.setAttribute("teacherName",teacherName);
                session.setAttribute("userName",teacherName.substring(0,1)+"老师");
                session.setAttribute("accountType","教师账户");
                return "redirect:/teacherNotice";

            }
            else {
                //设置登录失败的信息
                model.addAttribute("msg","账号密码错误!");
                //回到登录页面
                return "index";
            }
        }

            //学生登录
            String myCode = redisTemplate.opsForValue().get(QQ);
            if (code.equals(myCode)){
                //设置当前学生身份
                session.setAttribute("userName",studentName);
                session.setAttribute("accountType","学生账户");
                return "redirect:/teacherNotice";
            }else {
                model.addAttribute("msg","账号密码错误!");
                //回到登录页面
                return "index";
            }
    }



    //退出登录
    @GetMapping("/tuichu")
    public String logOut(HttpSession session){
        session.removeAttribute("userName");
        return "redirect:/";
    }




}
