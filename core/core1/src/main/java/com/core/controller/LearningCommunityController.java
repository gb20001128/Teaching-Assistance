package com.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.Service.ProblemService;
import com.core.mapper.CommentMapper;
import com.core.mapper.ProblemMapper;
import com.core.utils.FileUtils;
import com.core.utils.OSSUtils;
import com.core.utils.TimeUtils;
import common.domain.Comment;
import common.domain.Problem;
import common.domain.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/23 9:38
 */

@RestController
public class LearningCommunityController {


    @Autowired
    ProblemService problemService;

    @Autowired
    CommentMapper commentMapper;


    @RequestMapping("/learningCommunity")
    public Page learningCommunity(@RequestParam(value = "pn",defaultValue ="1") Integer pn){


        //分页查询数据
        Page<Problem> communityPage=new Page<>(pn,2);//规定当前的页码和记录数大小

        QueryWrapper<Problem> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        //分页查询的结果
        Page<Problem> page=problemService.page(communityPage,queryWrapper);
        //请求页的页码大于总页数,就设置当前页数为总页数
        if(pn>page.getPages()){
            communityPage=new Page<>(page.getPages(),2);
            page=problemService.page(communityPage,queryWrapper);
        }


        return page;

    }

    @RequestMapping("/learningCommunity/{id}")
    public Problem learningCommunityById(@PathVariable("id") Integer id){
        Problem problem = problemService.getProblem(id);
        return problem;

    }


    //微服务之间调用,参数是MultipartFile,应该这样写,consumes和@RequestPart要注意
    @PostMapping(value = "/uploadProblem",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean uploadProblem(@RequestPart("problemPhoto") MultipartFile problemPhoto,
                                 @RequestParam("problem") String problem,
                                 @RequestParam("userName") String userName){


        //String fileName = FileUtils.upload2(problemPhoto);原来把图片上传到tomcat的时候
        String fileName = OSSUtils.upload(problemPhoto);

        Problem myProblem=new Problem();

        myProblem.setName(userName);
        myProblem.setContent(problem);
        myProblem.setPhotoName(fileName);

        String time = TimeUtils.getTimeNow();
        myProblem.setTime(time);

        boolean save = problemService.save(myProblem);

        //追加评论
        Integer id = myProblem.getId();

        Comment comment=new Comment();
        comment.setName(userName);
        comment.setContent("大神求助~~~");
        comment.setTime(time);
        comment.setPid(id);

        commentMapper.insert(comment);

        return save;
    }



    @RequestMapping("/searchCommunity/{pn}/{searchWhat}")
    public Page searchCommunity(@PathVariable("searchWhat") String searchWhat,
                                @PathVariable("pn") Integer pn){

        QueryWrapper<Problem> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .like("content", searchWhat)
                .orderByDesc("id");

        //分页查询数据
        Page<Problem> problemPage=new Page<>(pn,2);//规定当前的页码和记录数大小


        //分页查询的结果
        Page<Problem> page=problemService.page(problemPage,queryWrapper);
        //请求页的页码大于总页数,就设置当前页数为总页数
        if(pn>page.getPages()){
            problemPage=new Page<>(page.getPages(),2);
            page=problemService.page(problemPage,queryWrapper);
        }

        return page;
    }


    @RequestMapping("/answerQuestion/{id}/{content}/{userName}")
    public  Integer answerQuestion(@PathVariable("id") Integer id,
                                  @PathVariable("content") String content,
                                  @PathVariable("userName") String userName){

        Comment comment = new Comment();
        comment.setName(userName);
        comment.setContent(content);
        String time = TimeUtils.getTimeNow();
        comment.setTime(time);
        comment.setPid(id);

        int insert = commentMapper.insert(comment);

        return insert;

    }


    //点赞评论
    @RequestMapping("/like/{id}")
    public  boolean likeComment(@PathVariable("id") Integer id){


        int i = commentMapper.likeComment(id);

        if (i==1)
            return true;
        else
            return false;

    }


    @RequestMapping("/deletelearningCommunity/{id}")
    public  Boolean deletelearningCommunity(@PathVariable("id") Integer id){

        Problem problem = problemService.getProblem(id);
        String photoName = problem.getPhotoName();

        OSSUtils.deleteObject(photoName);

        boolean b = problemService.removeById(id);

        Map<String,Object> map=new HashMap<>();
        map.put("pid",id);
        commentMapper.deleteByMap(map);

        return b;

    }



    //删除评论
    @RequestMapping("/deleteMyComment/{id}")
    public  boolean deleteMyComment(@PathVariable("id") Integer id){

        int i = commentMapper.deleteById(id);

        if (i>0)
            return true;
        else
            return false;

    }

}
