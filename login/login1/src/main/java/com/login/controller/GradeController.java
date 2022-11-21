package com.login.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.login.myhandler.BlockHandler;
import com.login.service.CoreService;
import common.domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/21 14:56
 */

@Controller
public class GradeController {

    @Autowired
    CoreService coreService;


    //查询成绩表
    @RequestMapping("/grade")
    public String grade(Model model){

        List<Grade> grades = coreService.getGrades();
        model.addAttribute("grades",grades);
        return "grade";
    }


    //获得修改成绩时那个学生的成绩
    @RequestMapping("/getGrade/{id}")
    public String updateGrade(@PathVariable("id") Integer id, Model model, HttpSession session){

        String accountType = session.getAttribute("accountType") + "";

        if (accountType.equals("教师账户")) {

            Grade grade = coreService.getGradeById(id);
            model.addAttribute("grade", grade);
            model.addAttribute("id", id);

            return "updateGrade";
        }
        model.addAttribute("msg"," 您没有权限修改!");
        return "forward:/grade";
    }



    //更新修改后的成绩
    @RequestMapping("/updateThisGrade")
    public String updateThisGrade(Grade grade){

        int i = coreService.updateThisGrade(grade);

        return "redirect:/grade";
    }


}
