package com.login.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.login.service.CoreService;
import common.domain.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/23 9:54
 */

@Controller
public class LearningCommunityController {


    @Autowired
    CoreService coreService;



    //查询学习社区
    @RequestMapping("/learningCommunity")
    public  String learningCommunity(@RequestParam(value = "pn",defaultValue ="1") Integer pn,
                                     String searchWhat,
                                     Model model){
        //搜索查询分页
        if (searchWhat!=null){
            Page page = coreService.searchCommunity(searchWhat, pn);
            model.addAttribute("page",page);
        }
        //全部查询分页
        else {
            Page page = coreService.learningCommunity(pn);
            model.addAttribute("page", page);
        }

        return "learningCommunity";
    }


    //查看某个问题的评论区
    @RequestMapping("/learningCommunity/{id}")
    public  String learningCommunityById(@PathVariable("id") Integer id, Model model){

        Problem problem = coreService.learningCommunityById(id);
        model.addAttribute("problem",problem);

        return "comment";
    }


    //发布问题
    @RequestMapping("/uploadProblem")
    public  String uploadProblem(MultipartFile problemPhoto, String problem, HttpSession session){

        String userName = session.getAttribute("userName") + "";

        Boolean b = coreService.uploadProblem(problemPhoto, problem, userName);



        return "redirect:/learningCommunity";
    }



    //发评论
    @RequestMapping("/answerQuestion")
    public  String answerQuestion( Integer id, String content, HttpSession session,Model model){

        String userName = session.getAttribute("userName") + "";
         coreService.answerQuestion(id, content, userName);

        Problem problem = coreService.learningCommunityById(id);
        model.addAttribute("problem",problem);
        return "comment";
    }


    //点赞评论
    @RequestMapping("/like/{id}/{pid}")
    public  String like(@PathVariable("id") Integer id,@PathVariable("pid") Integer pid){


        boolean b = coreService.likeComment(id);

        return "redirect:/learningCommunity/"+pid;
    }


    //删除某个问题
    @RequestMapping("/deletelearningCommunity/{id}/{name}")
    public  String deletelearningCommunity(@PathVariable("id") Integer id,
                                            @PathVariable("name") String name,
                                            HttpSession session,Model model){

        String userName = session.getAttribute("userName") + "";
        String accountType = session.getAttribute("accountType") + "";

       if (userName.equals(name)||accountType.equals("教师账户")){
           Boolean b = coreService.deletelearningCommunity(id);
       }
       else
           model.addAttribute("msg","您没有权限");

       return "forward:/learningCommunity";
    }


}
