package com.login.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.login.config.MultipartSupportConfig;
import common.domain.Grade;
import common.domain.Notice;
import common.domain.Problem;
import common.domain.Resources;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

//configuration属性这样写能解决feign微服务之间调用MultipartFile的问题
@FeignClient(value = "cloud-core",configuration = MultipartSupportConfig.class)
public interface CoreService {


    //教师通知
    @RequestMapping("/selectAllNotice")
    List<Notice> selectAllNotice();

    @RequestMapping("/sendNotice/{userName}/{notice}")
    boolean sendNotice(@PathVariable("userName") String userName,
                       @PathVariable("notice") String notice);

    @RequestMapping("/receiveNotice/{id}")
    int receiveNotice(@PathVariable("id") Integer id);

    @RequestMapping("/deleteNotice/{id}")
     Boolean deleteNotice(@PathVariable("id") Integer id);




    //成绩统计
    @RequestMapping("/getGrades")
    List<Grade> getGrades();

    @RequestMapping("/getGradeById/{id}")
    Grade getGradeById(@PathVariable("id") Integer id);

    @RequestMapping("/updateThisGrade")
     int updateThisGrade(@RequestBody Grade grade);




    //课件资源
    @RequestMapping("/resources")
     Page resources(@RequestParam(value = "pn",defaultValue ="1") Integer pn);

    @RequestMapping("/downResources/{id}")
     ResponseEntity<byte[]> downResources(@PathVariable("id") Integer id);

    @PostMapping(value = "/uploadResources",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     Boolean uploadResources(@RequestPart("resources") MultipartFile resources,@RequestParam("teacherName") String teacherName);

    @RequestMapping("/selectResourcesByName/{teacherName}")
     List<Resources> selectResourcesByName(@PathVariable("teacherName") String teacherName);

    @RequestMapping("/deleteMyResources/{id}")
     Boolean deleteMyResources(@PathVariable("id") Integer id);

    @RequestMapping("/search/{searchWhat}/{pn}")
     Page searchResources(@PathVariable("searchWhat") String searchWhat,
                                @PathVariable("pn") Integer pn);



    //学习社区
    @RequestMapping("/learningCommunity")
     Page learningCommunity(@RequestParam(value = "pn",defaultValue ="1") Integer pn);

    @RequestMapping("/learningCommunity/{id}")
     Problem learningCommunityById(@PathVariable("id") Integer id);

    @PostMapping(value = "/uploadProblem",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     Boolean uploadProblem(@RequestPart("problemPhoto") MultipartFile problemPhoto,
                                 @RequestParam("problem") String problem,
                                 @RequestParam("userName") String userName);

    @RequestMapping("/searchCommunity/{pn}/{searchWhat}")
     Page searchCommunity(@PathVariable("searchWhat") String searchWhat,
                                @PathVariable("pn") Integer pn);

    @RequestMapping("/answerQuestion/{id}/{content}/{userName}")
     Integer answerQuestion(@PathVariable("id") Integer id,
                                   @PathVariable("content") String content,
                                   @PathVariable("userName") String userName);
    @RequestMapping("/like/{id}")
      boolean likeComment(@PathVariable("id") Integer id);

    @RequestMapping("/deletelearningCommunity/{id}")
      Boolean deletelearningCommunity(@PathVariable("id") Integer id);
}
